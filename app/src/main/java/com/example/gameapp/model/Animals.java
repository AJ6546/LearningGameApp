package com.example.gameapp.model;

public class Animals {
    int id;
    String description;
    String name;

    public Animals(int id) {
        this.id=id;
        switch(this.id)
        {
            case 0:
                this.name="Barn Owl";
                this.description="Barn owls are the most well-known member " +
                        "family of owls known for their stark white faces and " +
                        "haunting calls";
                break;
            case 1:
                this.name="Antelope";
                this.description="Renew their horns every year!";
                break;
            case 2:
                this.name="Bat";
                this.description="Detects prey using echolocation!";
                break;
            case 3:
                this.name="Bear";
                this.description="There are 8 different species!";
                break;
            case 4:
                this.name="Cheetah";
                this.description="The fastest land mammal in the world!";
                break;
            case 5:
                this.name="Chicken";
                this.description="First domesticated more than 10,000 years ago!";
                break;
            case 6:
                this.name="Crocodile";
                this.description="Known to eat pebbles to aid digestion and buoyancy!";
                break;
            case 7:
                this.name="Deer";
                this.description="There are around 40 different species!";
                break;
            case 8:
                this.name="Elephant";
                this.description="Spends around 22 hours a day eating!\n";
                break;
            case 9:
                this.name="Falcon";
                this.description="The falcon is faster than any other animal on earth, both on land and in the air";
                break;
            case 10:
                this.name="Eagle";
                this.description="Has exceptional eyesight!";
                break;
        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
