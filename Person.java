package org.example.assassinsimulatorapp;

public class Person {

    public String name;
    public Person target;
    public boolean isKilled;
    public int numKills;

    public Person(String nm) {
        this.name = nm;
        this.target = null;
        this.isKilled = false;
    }

    public Person(String nm, Person target) {
        this.name = nm;
        this.target = target;
        this.isKilled = false;
    }

    public void changeName(String nm) {
        this.name = nm;
    }

    public boolean isDead() {
        return this.isKilled;
    }

    public void setTarget(Person p) {
        this.target = p;
    }

    public boolean hasTarget(Person p) { return this.target == p; }

    public Person assassinated() {
        this.isKilled = true;
        return this.target;
    }

    public String getName() {
        return this.name;
    }

    public String killed(Person p) throws RuntimeException {
        if (this.target != p) {
            throw new RuntimeException();
        }
        this.numKills++;
        this.target = this.target.assassinated();
        return this.name + "'s new target is " + this.target.getName();
    }

    public int getNumKills() {
        return this.numKills;
    }


}
