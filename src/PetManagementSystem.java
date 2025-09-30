public class PetManagementSystem {

    static class PetService {
        private static final double BASE_FEE = 50.0;
        private static final double VACCINATION_FEE = 25.0;
        private static final double GROOMING_FEE = 30.0;

        public double calculateFee() {
            return BASE_FEE;
        }

        public double calculateFee(boolean withVaccination) {
            if (withVaccination) {
                return BASE_FEE + VACCINATION_FEE;
            }
            return BASE_FEE;
        }

        public double calculateFee(boolean withVaccination, boolean withGrooming) {
            double total = BASE_FEE;
            if (withVaccination) {
                total += VACCINATION_FEE;
            }
            if (withGrooming) {
                total += GROOMING_FEE;
            }
            return total;
        }

        public double calculateFee(String emergencyType) {
            return 200.0;
        }
    }

    abstract static class Pet {
        protected String name;
        protected int age;

        public Pet(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public abstract void makeSound();

        public void displayInfo() {
            System.out.println("Pet Name: " + name + ", Age: " + age);
            System.out.print(name + " says: ");
            makeSound();
            System.out.println();
            System.out.println();
        }
    }

    static class Dog extends Pet {
        public Dog(String name, int age) {
            super(name, age);
        }

        @Override
        public void makeSound() {
            System.out.print("Woof! Woof!");
        }
    }

    static class Cat extends Pet {
        public Cat(String name, int age) {
            super(name, age);
        }

        @Override
        public void makeSound() {
            System.out.print("Meow! Meow!");
        }
    }

    static class Bird extends Pet {
        public Bird(String name, int age) {
            super(name, age);
        }

        @Override
        public void makeSound() {
            System.out.print("Tweet! Tweet!");
        }
    }

    interface Trainable {
        void performTrick();
    }

    static class TrainableDog extends Dog implements Trainable {
        public TrainableDog(String name, int age) {
            super(name, age);
        }

        @Override
        public void performTrick() {
            System.out.println("Training " + name + ": Sits and shakes hands");
        }
    }

    static class TrainableBird extends Bird implements Trainable {
        public TrainableBird(String name, int age) {
            super(name, age);
        }

        @Override
        public void performTrick() {
            System.out.println("Training " + name + ": Flies in circles and lands on perch");
        }
    }

    static class Trainer {
        public void trainAnimal(Trainable animal) {
            animal.performTrick();
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Pet Clinic!");
        System.out.println("==================================================");

        Pet[] pets = {
                new Dog("Buddy", 3),
                new Cat("Whiskers", 2),
                new Bird("Tweety", 1)
        };

        for (Pet pet : pets) {
            pet.displayInfo();
        }

        System.out.println("==================================================");

        PetService service = new PetService();
        System.out.println("\nBasic checkup: $" + service.calculateFee());
        System.out.println("Checkup with vaccination: $" + service.calculateFee(true));
        System.out.println("Full service: $" + service.calculateFee(true, true));
        System.out.println("Emergency: $" + service.calculateFee("Emergency"));

        System.out.println("\n==================================================");

        System.out.println("\nTraining Session Started!");
        System.out.println("==================================================");
        Trainer trainer = new Trainer();
        Trainable[] trainablePets = {
                new TrainableDog("Buddy", 3),
                new TrainableBird("Tweety", 1)
        };

        for (Trainable animal : trainablePets) {
            trainer.trainAnimal(animal);
        }
    }
}