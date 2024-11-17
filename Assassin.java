package org.example.assassinsimulatorapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Assassin {

    public ArrayList<Person> competitors;
    public ArrayList<Person> allInitialCompetitors;

    public Assassin() {
        this.competitors = new ArrayList<>();
        this.init();
    }

    public Person addPerson(String nm) {
        Person p = new Person(nm);
        this.competitors.add(p);
        return p;
    }

    public void setTarget(String name, String nm) {
        try {
            Person p = this.getPerson(name);
            Person target = this.getPerson(nm);
            p.setTarget(target);
        } catch (Exception e) {
            System.out.println("Trying to set the target of/to a person that doesn't exist.");
        }
    }

    public Person getPerson(String name) throws Exception{
        for (Person p : this.competitors) {
            if (name.equals(p.getName())) {
                return p;
            }
        }
        throw new Exception("Person did not exist.");
    }

    public String assassination(String assassin, String prey) {
        try {
            String result = this.getPerson(assassin).killed(this.getPerson(prey));
            competitors.remove(this.getPerson(prey));
            return result;
        } catch (Throwable e) {
            return "The person killed was not the assassin's target.";
        }
    }

    public String findTarget(String nm) {
        try {
            Person p = this.getPerson(nm);
            return p.target.getName();
        } catch (Exception e) {
            return "This person is not a competitor.";
        }
    }

    public String findTarget(Person p) {
        if (this.competitors.contains(p)) return p.target.getName();
        else return null;
    }

    public String competitorsLeft() {
        String result = "";
        Collections.shuffle(competitors);
        for (Person p : this.competitors) {
            result += p.getName() + "\n";
        }
        return result;
    }

    public Person whoHasTarget(Person p) {
        for (Person pl : this.competitors) {
            if (pl.hasTarget(p)) return pl;
        }
        return null;
    }

    public void removeInactivePlayers() {
        ArrayList<Person> toRemove = new ArrayList<>();
        for (Person p : this.competitors) {
            if (p.getNumKills() == 0) {
                if (this.whoHasTarget(p) != null) {
                    this.whoHasTarget(p).setTarget(p.assassinated());
                    toRemove.add(p);
                }
            }
        }
        this.competitors.removeAll(toRemove);
    }

    public String competitorsAndTargetsLeft() {
        String result = "";
        Collections.shuffle(competitors, new Random(2));
        for (Person p : this.competitors) {
            result += p.getName() + ": " + this.findTarget(p) + "\n";
        }
        return result;
    }

    public String leaderBoard() {
        String result = "";
        ArrayList<Person> sortedList = (ArrayList<Person>) this.allInitialCompetitors.clone();
        Collections.sort(sortedList, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return Integer.compare(p2.getNumKills(), p1.getNumKills());
            }

            @Override
            public boolean equals(Object o) {
                return false;
            }
        });
        for (int i = 0; i < 5; i++) {
            result += sortedList.get(i).getName() + ": " + sortedList.get(i).getNumKills() + "\n";
        }
        return result;
    }

    public void shuffleTargets() {
        ArrayList<Person> targets = (ArrayList<Person>) this.competitors.clone();
        Collections.shuffle(targets);
        for (Person p : this.competitors) {
            Person target = p;
            while (target == p) {
                target = targets.getFirst();
                Collections.shuffle(targets);
            }
            p.setTarget(target);
            targets.remove(target);
        }
    }

    public void init() {
        String csvFile = "/Users/shayaanchaudhary/Desktop/Education/Brown/Miscellaneous/BUGS/AssassinSimulatorApp/src/main/java/org/example/assassinsimulatorapp/assassinTargets.csv";
        ArrayList<String> targets = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line = br.readLine();
            while(line != null) {
                String[] personData = line.split(",");
                String personName = personData[0].trim();
                String targetName = personData[1].trim();
                targets.add(targetName);
                this.addPerson(personName);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found!");
        } catch (IOException f) {
            //
        }
        for (int i = 0; i < this.competitors.size(); i++) {
            try {
                competitors.get(i).setTarget(this.getPerson(targets.get(i)));
            } catch (Exception e) {
                System.out.println("target did not exist");
            }
        }
        this.allInitialCompetitors = (ArrayList<Person>) this.competitors.clone();
        this.assassination("Brando", "Manuela");
        this.assassination("Zach", "Anastasio");
        this.assassination("Nick", "JoseP");
        this.assassination("Symeon", "Eugenio");
        this.assassination("Fope", "Angie");
        this.assassination("Lily", "Joe");
        this.assassination("Brando", "Fope");
        this.assassination("Symeon", "Henry");
        this.assassination("Symeon", "Lean");
        this.assassination("Matteo", "Isabella");
        this.assassination("Wendel", "Charles");
        this.assassination("Isabel", "Nishani");
        this.assassination("Symeon", "Serena");
        this.assassination("Jaden", "Andrea");
        this.assassination("Jaden", "Lola");
        this.assassination("Lara", "ElsaL");
        this.assassination("Jaden", "Lara");
        this.assassination("Zein", "Joel");
        this.assassination("Ollie", "Maya");
        this.assassination("Zein", "AlexS");
        this.assassination("Sam", "Nick");
        this.assassination("Mathilda", "Tate");
        this.assassination("ElsaC", "Sam");
        this.assassination("ElsaC", "Emma");
        this.assassination("Izzy", "ElsaC");
        this.assassination("Joseph", "Lily");
        this.assassination("Symeon", "Cam");
        this.assassination("Cata", "Zoe");
        this.assassination("Josh", "Gaby");
        this.assassination("Cata", "Josh");
        this.assassination("Joseph", "Anya");
        this.assassination("Joseph", "Isabel");
        this.assassination("Rose", "Reed");
        this.assassination("Nate", "Jesalina");
        try {
            this.getPerson("Symeon").changeName("ElsaC");
        } catch (Exception e) {
            //
        }
        this.assassination("Nina", "Joseph");
        this.assassination("Jaden", "Rose");
        this.assassination("Zein", "Zach");
        this.assassination("Brando", "Mathilda");
        try {
            this.getPerson("Cata").changeName("Mimi");
        } catch (Exception e) {
            //
        }
        this.assassination("Mimi", "Singer");
        this.assassination("Jaden", "Annabel");
        this.assassination("Izzy", "Jaden");
        this.assassination("Nate", "Nina");
        this.assassination("Mimi", "Zein");
        this.assassination("Talia", "Chris");
        this.assassination("ElsaC", "Matt");
        this.assassination("Marco", "Coco");
        this.assassination("Talia", "Ollie");
        this.assassination("Talia", "Izzy");
        this.removeInactivePlayers();
        this.shuffleTargets();
    }
}