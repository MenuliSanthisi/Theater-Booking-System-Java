//Task 10
public class Ticket {
    int row;
    int seat;
    double price;
    Person person;

    public Ticket(int row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }
    public int getRow(){
        return row;
    }
    public int getSeat(){

        return seat;
    }
    public double getPrice(){

        return price;
    }
    //Task 11
    public void print(){
        System.out.println("Name: "+person.getName());
        System.out.println("Surname: "+person.getSurname());
        System.out.println("Email: "+person.getEmail());
        System.out.println("Row: "+getRow());
        System.out.println("Seat: "+getSeat());
        System.out.println("Price: "+getPrice());
    }
}


