public class Exercise {
    private String name;
    private String description;
    private int reps;
    private int sets;

    public Exercise(String name, String desc, int reps, int sets) {
        this.reps = reps;
        this.name = name;
        this.description = desc;
        this.sets = sets;
    }

    public int getReps() { return reps; }
    public void setReps(int reps) { this.reps = reps; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getSets() { return sets; }
    public void setSets(int sets) { this.sets = sets; }

    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }

    public void print() {
        System.out.println(this.sets + " sets, " + this.reps + " reps of " + this.name + ":\n\t" + this.description + "\n");
    }

}