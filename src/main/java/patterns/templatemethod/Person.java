package patterns.templatemethod;

public class Person {

    private int skills;
    private int abilities;
    private int knowledge;
    private int intelligence;

    public Person(int skills, int abilities, int knowledge, int intelligence) {
        this.skills = skills;
        this.abilities = abilities;
        this.knowledge = knowledge;
        this.intelligence = intelligence;
    }

    public int getSkills() {
        return skills;
    }

    public int getAbilities() {
        return abilities;
    }

    public int getKnowledge() {
        return knowledge;
    }

    public int getIntelligence() {
        return intelligence;
    }
}