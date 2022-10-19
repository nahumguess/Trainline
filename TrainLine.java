/**
 * A train line to demonstrate the concept of a simple linked list.
 */

public class TrainLine {

    /** The only field of this class!!!!!! */
    private TrainStation head;

    /**
     * Default constructor; not needed really but never hurts to write one.
     */
    public TrainLine() {
        this.head = null;
    }  // default constructor

    /**
     * Adds a train station to the end of a train line.
     *
     * @param name Name of station to add, assuming this is a unique
     *             station for this train line.
     */
    public void addStation(String name) {
        // The new station to add
        TrainStation station = new TrainStation(name);
        // Where to add it?
        if (this.head == null) {
            // If there is no station in the line yet, new station becomes head
            this.head = station;
        } else {
            // Traverse the train line from the head station ...
            TrainStation current = this.head;
            // ..until you find the last station.
            while (current.hasNext()) {
                // If there is a station after this, move current pointer there.
                current = current.getNext();
            }
            // Loop ends when current is at the end of line; add new station here
            current.setNext(station);
        }
    }  // method addStation


    /**
     * Tells if a station with a specified name exista in the train line.
     *
     * @param name String with name of the station to search for.
     * @return true if station with specified name exists, false otherwise.
     */
    public boolean lineHasStation(String name) {
        // Assume station not present in train line.
        boolean found = false;
        // Prepare to traverse the linked list from its head on.
        TrainStation current = this.head;
        // Loop continues while there are stations to explore and name has not been found.
        while (!found && current != null) {
            // Update found variable
            found = current.getName().equals(name);
            // Move to the next station
            current = current.getNext();
        }
        return found;
    }  // method lineHasStation


    /**
     * Inserts a new station before a specific station.
     *
     * The method assumes that station with beforeName exists and therefore does
     * not offer any protection against a null pointer exception that may occur
     * in the while loop.
     *
     * @param beforeName name of the station where the new station will be
     *                   inserted before.
     * @param newName name of new station to insert
     */
    public void insertBefore(String beforeName, String newName) {
        // Create the new object to insert
        TrainStation newStation = new TrainStation(newName);
        // Inserting before the head station requires separate handling
        if (beforeName.equals(head.getName())) {
            // Have the new station point to head
            newStation.setNext(head);
            // And move the head label to the new station
            head = newStation;
        } else {
            // If we are not inserting before the head, prepare to traverse the list
            TrainStation current = this.head;
            // Looking for a station whose next station is the one we are inserting prior to
            while (!current.getNext().getName().equals(beforeName)) {
                current = current.getNext();
            }
            newStation.setNext(current.getNext());
            current.setNext(newStation);
        }
    }  // method insertBefore

    /**
     * Inserts a new station after a specific station.
     *
     * The method assumes that station with afterName exists and therefore does
     * not offer any protection against a null pointer exception that may occur
     * in the while loop. Method also assumes that there is no station with
     * newName in the train line, and therefore does not offer any protection
     * against a duplicate train station object.
     *
     * @param afterName name of the station where the new station will be
     *                   inserted before.
     * @param newName name of new station to insert
     */
    public void insertAfter(String afterName, String newName) {
        TrainStation newStation = new TrainStation(newName);
        TrainStation current = this.head;
        while (!current.getName().equals(afterName)) {
            current = current.getNext();
        }
        newStation.setNext(current.getNext());
        current.setNext(newStation);
    }  // method insertAfter


    /**
     * Deletes a station specified by name.
     * If a station that is not a part of the line is asked to be deleted, nothing will be deleted.
     *
     * @param name String with name of station to delete.
     */
    public void delete(String name) {
        //if the train line is not empty and the head station is the station to be deleted, the conditions of this if statement are satisfied.
        if (this.head != null && this.head.getName().equals(name)) {
            // Remember what's after the head; we'll need it.
            TrainStation oldHeadPointedTo = this.head.getNext();
            // Make the current head point to nowhere.
            this.head.setNext(null);
            // Re-designate the head to the station after the old head.
            this.head = oldHeadPointedTo;
        //if the head is empty/the train line is empty and the head is not the station to be deleted, proceed
        } else if (this.head != null && !this.head.getName().equals(name)){
            // Traverse line and fine station prior to one to be deleted.
            TrainStation prior = this.head;
            /*
            Traverse the list with a cursor called "previous" to signify that
            we are looking for the station prior to the one we wish to delete.
            Assume that d is the station to delete. Then
               d = previous.next
            To remove d, we need to connect the station before d (that would be
            the previous station) to the station after d (that would be d.next).
            Therefore, we want the assignment:
               previous.next = d.next
            Replacing d with previous.next above, we get
               previous.next = previous.next.next
            Or, using the get/set methods for the TrainStation object:
               previous.setNext(previous.getNext().getNext())
             */
            while ( prior.hasNext() && (!prior.getNext().getName().equals(name)) ) {
                prior = prior.getNext();
            }
            /*
            Loop ends when it cannot find a station whose next station is the one
            to delete, or when it finds that station. We don't know which of the
            two conditions terminated the loop. The if statement below finds out.
            If the while loop delivers us to the last station, then there is no
            station to delete. Because to delete a station, the loop must deliver
            us to the station prior. The last station, by definition, is not prior
            to any station.
             */
            if (prior.hasNext()) {
                // Pointer prior has a non-null next, so it is not the last station.
                // Deletion can proceed.
                prior.setNext(prior.getNext().getNext());
            }
        }
    }  // method delete


    /**
     * This method will delete a train station as well as the train station after it when given the name of the first train station to delete
     * If the last station is being asked to delete, the last station will be deleted.
     * If a station that is not a part of the line is asked to be deleted, nothing will be deleted.
     *
     * @param name String with name of first station to delete.
     */
    public void deleteDelete(String name) {
        //if the train line is not empty and the head station is the station to be deleted, the conditions of this if statement are satisfied.
        if (this.head != null && this.head.getName().equals(name)) {
            //Create a new train station that is equal to the station after the head. This will represent where the current head is pointing to.
            TrainStation oldHeadPointedTo;

            //this loop deletes the head station as well as the station after it. Should iterate twice because a deletion is being done twice
            for(int i = 0; i < 2; i++){
                //set oldHeadPointed to equal to the value that the current head is pointing to.
                oldHeadPointedTo = this.head.getNext();
                //Make the head point nowhere. This signifies deletion.
                this.head.setNext(null);
                //Set the head equal to where it used to point to.
                this.head = oldHeadPointedTo;
            }
        }

        //if the head is empty/the train line is empty and the head is not the station to be deleted, the conditions of this if statement are satisgied
        else if (this.head != null && !this.head.getName().equals(name)){
            //create a new station equal to the head. This will serve as the start to traversing the line.
            TrainStation prior = this.head;

            //while the station after prior is not null and the station after prior is not the first station to be deleted, this loop should iterate.
            while ( prior.hasNext() && (!prior.getNext().getName().equals(name)) ) {
                //set prior equal to its next station to traverse the list/line.
                prior = prior.getNext();
            }

            //If prior has a next station and if the station after prior has no next station, only delete the station after prior.
            if (prior.hasNext() && !prior.getNext().hasNext()){
                prior.setNext(prior.getNext().getNext());
            }

            //if prior has a next station, delete the station after prior and the station after that.
            else if (prior.hasNext()){
                prior.setNext(prior.getNext().getNext().getNext());
            }
            }
    }


    /**
     * String representation of the TrainLine.
     *
     * @return String with stations neatly arranged
     */
    public String toString() {
        // Introducing the use of StringBuilder objects
        StringBuilder sb = new StringBuilder();
        if (this.head == null) {
            // If head is null, the train line is empty.
            sb.append("This train line has no stations.");
        } else {
            // There are stations. Prepare to traverse the line.
            TrainStation current = this.head;
            // Get the name of every station and add it to the StringBuilder object.
            while (current.hasNext()) {
                sb.append(String.format("%s --> ", current.getName()));
                // The "-->" needs to be a class constant not a "magic" value,
                // and I'll fix it later.
                current = current.getNext();
            }
            // The loop ends at the last station, which is very useful because
            // it allows us to omit the "-->" decoration after it.
            sb.append(current.getName());
        }
        // Convert the StringBuilder object to a String, using the StringBuilder
        // class's toString method, and return it.
        return sb.toString();
    }  // method toString


    /*
    For illustration only. A simple method to create a string representation of
    a TrainLine object, using just strings.
     */
    public String plainToString() {
        String string = new String();
        if (this.head == null) {
            string = "This train line has no stations.";
        } else {
            TrainStation current = this.head;
            while (current.hasNext()) {
                string = string + current.getName() + " --> ";
                current = current.getNext();
            }
            string = string + current.getName();
        }
        return string;
    }  // method plainToString


    /*
    For illustration only. The same simple method from above, slightly modified
    to use the format() method of the String class.
     */
    public String formattedToString() {
        String string = new String();
        if (this.head == null) {
            string = "This train line has no stations.";
        } else {
            TrainStation current = this.head;
            while (current.hasNext()) {
                string = String.format("%s --> ", current.getName());
                current = current.getNext();
            }
            string = string + current.getName();
        }
        return string;
    }  // method formattedToString

}