## Sea.java

### A) Refined Puffins [Java]

#### Problem A1: The Big Sea Lock.
When you solved Problem D1 in Exercise 1, you probably made each method in the Sea class synchronized, or something similar – if you look at the solution we've provided here that's what we have done. For this Problem, you can use the included solution or replace it with your original solution from Exercise 1.
Have a look at Sea::grabFish() – is it possible to access different locations concurrently? E.g., if one thread calls sea.grabFish(1) and another calls sea.grabFish(2) will one of them have to wait for the other to complete? That is probably a bad idea – if we have a big ocean and a million puffins, it would be a pity if the grabFish() method became a bottleneck.¹ In this case, it's fairly easy to solve this: instead of storing Fish or null directly, we can have each sea location store a SeaSector object, and let the SeaSector keep track of whether it has a Fish or not. Since every Java object has its own intrinsic lock, and every location its own SeaSector object, we can now lock each location individually instead of locking the whole Sea.
Another, more advanced solution would be to use Java's AtomicReferenceArray which lets you do atomic updates to individual array elements.

Modify the implementation of Sea so different sea sectors can be accessed concurrently.
Don't worry about synchronizing startDay(), we assume that it's only called by a single thread. (The barrier ensures this.)
You do have to be careful with the release() method, though, and make sure it works correctly even if another thread calls release() or grabFish() concurrently.
Explain your findings and solution very briefly, in ANSWERS.md.

Note: “Big locks” are a common problem – for example, the Big Kernel Lock in Linux (removed in 2011) or global locks in some programming languages.
¹ We can see the effect by adding a small delay to Sea::grabFish(). For example, with a 15 ms delay and default parameters, running DiningPuffinsDemo will take somewhere around 40 seconds if Sea::grabFish() is synchronized, and around 10–20 seconds with individual locks for each sector. With a larger sea and more fish, the effect is greater.

```

package inf214.oblig1.dining;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import inf214.oblig1.ThreadUtils;

/**
 * Simulates a sea with fish for the puffins to catch
 * 
 * The "sea" is a list with a fixed size, each index can store a fish or nothing
 * (<code>null</code>)
 *
 */
public class Sea {
	private final List<SeaSector> data = new ArrayList<>();
	private int leftOverFish = 0;
	private int fishPerDay;

	public Sea(int size, int fishPerDay) {

		// Adding all the sectors to the sea
		for (int i = 0; i < size; i++) {
			data.add(new SeaSector());
		}

		this.fishPerDay = fishPerDay;
	}

	/**
	 * At the start of each day, we reset the sea to a fixed number of fish, at
	 * random locations
	 * 
	 * @param fishPerDay
	 */
	public synchronized void startDay() {

		leftOverFish = leftOverFish + availFish();
		for (SeaSector sector : data) {
        	sector.grabFish(); // Clear out all fish
        }
		release(fishPerDay);
	}

	/**
	 * Check if there's a fish in the given "sector" (index)
	 * 
	 * @param sector
	 * @return true if grabFish(sector) would have return a non-null value
	 */
	public boolean hasFish(int sector) {
        return data.get(sector).hasFish();
        
	}
	

	/**
	 * @return number of fishes in the sea
	 */
	public synchronized int availFish() {
		int count = 0;
        for (SeaSector sector : data) {
			if (sector.hasFish()) {
				count++;
			}
        }
        return count;
	}

	/**
	 * Catch a fish if it's there
	 * 
	 * @param sector where to look for the fish
	 * @return a fish, or <code>null</code> if no fish was found at that location
	 */
	public Fish grabFish(int sector) {
		return data.get(sector).grabFish();
        
	}

	/**
	 * Release fish into the sea
	 * 
	 * @param nFish Number of fish to add
	 * @return Number of fish added
	 */
	public int release(int nFish) {
		int n = 0;

		for (int k = 0; k < nFish; k++) {
			int i = ThreadLocalRandom.current().nextInt(size());
			for (int j = 0; j < size(); j++) { // search for a free spot
				//reponer con peces en algunos sectores random
				SeaSector seaSector = data.get((i + j) % data.size());
				if (seaSector.addFish()) {
					n++;
					break;
				}
			}
		}
		return n;
	}

	/**
	 * @return Number of "sectors" (valid indices) in the sea
	 */
	public synchronized int size() {
		return data.size();
	}

	public synchronized int leftOverFish() {
		return leftOverFish;
	}

	public synchronized int fishPerDay() {
		return fishPerDay;
	}

}


```