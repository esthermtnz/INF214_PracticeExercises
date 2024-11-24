## BiologyStudent.java

### Problem A2: A more realistic (and complicated) ringing simulation
Recall the Ringing Puffins problem from Exercise 1 Problem C. In inf214.oblig1.ringing, you'll find a solution that uses Java's built-in BlockingQueue. In particular, MistNet::removeBird() will now wait until a bird is available before returning. We'll now add some complications:


Pliers are needed to attach the ring to a bird. The code in BiologyStudent::run() has been modified to provide each student with their own Pliers.
A Professor should supervise each student during the actual ringing process, to make sure they learn the proper technique and don't injure or stress the bird. In simulation terms, each student must call Professor::superviseRinging() when a ring is attached, or the simulation will fail its consistency check. (In real life, at least in Norway, ringing birds without proper training and a license is illegal; cf. Viltforskriften kap. 2)

We have fewer professors than students, so each student must find a professor to supervise before they can proceed with ringing. In simulation terms, each student must call sim.grabProfessor() to find a supervisor before ringing, then call sim.releaseProfessor() so another student can have a go.
Modify BiologyStudent so that each student is supervised by a Professor when ringing a bird.

RingingDemo and RingingTest should succeed without throwing an IllegalStateException.


### Problem A3: More complicated than A2
Due to budget cuts, the Biology Department can unfortunately no longer afford to buy enough pliers for all the students. Instead, the students have to borrow tools from a common toolbox.

Modify BiologyStudent and make each student call sim.grabPliers() / sim.releasePliers() to “borrow“ pliers when ringing a bird.

A student will now need to obtain three different things (pliers, a professor and a bird) in order to do their job. All three resources are limited, and we may need to wait for them.


Look at your implementation: Is there risk of a deadlock? (E.g., can we end up with students 1–3 holding pliers, and 4–5 having found professors, and none of them can proceed?)

Provoke a deadlock. You probably have to modify your code a little bit to succeed. Explain how you did it. (Note: In most cases where it deadlocks, the program should stop on its own after half a minute or so, but you may have to stop it manually.)

Fix the code so it doesn't deadlock.

```

package inf214.oblig1.ringing;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import inf214.oblig1.ThreadUtils;

/**
 * Simulation of a biology student marking birds with rings
 *
 */
public class BiologyStudent extends Thread {

	/**
	 * The rings we have available
	 */
	protected final List<String> allocatedRings;
	/**
	 * 
	 */
	protected final RingingDemo sim;
	protected final Pliers myOwnPliers;

	/**
	 * @param name           Student's name
	 * @param allocatedRings List of rings to use
	 * @param demo           simulator
	 */
	public BiologyStudent(RingingDemo sim, String name, List<String> allocatedRings) {
		super(name);
		this.sim = sim;
		this.allocatedRings = allocatedRings;
		this.myOwnPliers = new Pliers(getName()).reserve(this);

	}

	/**
	 * Implements student behaviour
	 */
	public void run() {
		while (!allocatedRings.isEmpty()) { // as longs as we have more rings
			Professor prof = null;
			Pliers tool = null;

			// A2. SOLUTION 1:
			while (tool == null)
				tool = sim.grabPliers(this);
			while (prof == null)
				prof = sim.grabProfessor(this);
			// A2. SOLUTION 2:
			// Alternatively, change poll() to take() in sim.grabProfessor() and
			// sim.grabPliers() –
			// this will make sure that we wait for the professors/pliers. (Though,
			// theoretically, we might still get a null if take() gets interrupted by
			// an InterruptedException, so a while loop is probably still a good idea.

			// A3. SOLUTION:
			// As long *everyone* acquires the professor and the pliers in the
			// same order, there won't be a deadlock. To provoke a deadlock,
			// we can make it possible to acquire the professor before the pliers:
			while(tool == null || prof == null) {
				if(tool == null)
					tool = sim.grabPliers(this);
				if (prof == null)
					prof = sim.grabProfessor(this);	
			}

			Bird bird = sim.net().removeBird(); // catch a bird
			if (bird != null) { // did we get one?
				// grab a ring, and attach it
				String ring = bird.attachRing(tool, allocatedRings.remove(0));
				// A2. SOLUTION: have the professor supervise ringing
				prof.superviseRinging(this, tool, bird, ring);
				RingingDemo.log("attached " + ring + " to " + bird + " with " + tool);
				// wait a little while before the next attempt
				ThreadUtils.delay(ThreadLocalRandom.current().nextInt(10) + 1);
			} else {

				Thread.onSpinWait(); // give other threads a chance to do something
			}

			if (tool != null && tool != myOwnPliers)
				sim.releasePliers(tool, this);
			// A2. SOLUTION: remember to free the professor after use
			sim.releaseProfessor(prof, this);

		}

	}

	public String toString() {
		return getName();
	}
}


```