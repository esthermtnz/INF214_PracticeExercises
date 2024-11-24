## SeaSector.java

```

package inf214.oblig1.dining;

public class SeaSector {
    private Fish fish;

    public synchronized boolean hasFish() 
    {
        return this.fish != null;
    }

    public synchronized Fish grabFish() 
    {
        Fish f = this.fish;
        this.fish = null;
        return f;
    }

    public synchronized boolean addFish() {
        if (this.fish != null){
            return false;
        }

        this.fish = new Fish();
        return true;
	}
}

```