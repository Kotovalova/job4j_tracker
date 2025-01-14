package ru.job4j.oop.inheritance.profession;

public class Engineer extends Profession {

    private boolean allowance;

    public Engineer(String name, String surname, String education,
                    String birthday, boolean allowance) {
        super(name, surname, education, birthday);
        this.allowance = allowance;
    }

    public boolean canWork() {
        return this.allowance;
    }
}
