package patterns.templatemethod;

public abstract class Equality {

    public void checkEquality() {
        Person tom = new Person(20, 30, 12, 90);
        Person jerry = new Person(20, 30, 12, 100);

        double tomRatio = getRatio(getPersonSum(tom));
        double jerryRatio = getRatio(getPersonSum(jerry));

        if (tomRatio > jerryRatio) {
            System.out.println("Tom jest lepszy!");
        } else {
            System.out.println("Jerry jest lepszy!");
        }
    }

    protected abstract double getRatio(double personSum);

    public double getPersonSum(Person person) {
        return Math.sqrt(person.getAbilities() + person.getIntelligence() + person.getSkills() + person.getKnowledge());
    }
}
