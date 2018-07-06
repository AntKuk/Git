class Automata {
    enum States {
        OFF, WAIT, ACCEPT, CHECK, COOK
    }

    private int cash;
    private String [] menu;
    private int [] prices;
    private States state;

    Automata () {
        this.cash = 0;
        this.menu = new String []{"black coffee", "coffee with milk", "latte macchiato"};
        this.prices = new int [] {20, 30, 40};
        this.state = States.OFF;
    }

    public void on () {
        this.state = States.WAIT;
    }

    public boolean off () {
        if (this.state == States.WAIT) {
            this.cash = 0;
            this.state = States.OFF;
            return true;
        }
        else return false;
    }

    public void coin (int money) {
        if((this.state != States.OFF) & (this.state == States.WAIT | this.state == States.ACCEPT)) {
            this.cash += money;
            this.state = States.ACCEPT;
        }
    }

    public String getMenu() {
        String a = "";
        if (this.state != States.OFF) {
            //String a = "";
            for (int i = 0; i < menu.length; i++) {

                a += menu[i] + "=" + prices[i] + '\n';
            }
            return a;
        }
        return a;
    }

    public String getState () {
        return state.toString();
    }

    public boolean choice (int pos) {

        if (this.state == States.ACCEPT) {
            if (check(pos)) {
                this.state = States.COOK;
                //getChange();
                return true;
            } else {
                this.state = States.CHECK;
                return false;
            }
        }
        else {
            this.state = States.WAIT;
            return false;
        }
    }

    public void cancel () {
        if(this.state == States.CHECK) {
            state = States.ACCEPT;
        }

        else if (this.state == States.ACCEPT) {
            getChange();
            this.state = States.WAIT;
        }
    }

    private boolean check (int a) {
        if (this.cash >= this.prices[a]) {
            this.cash -= this.prices[a];
            this.state = States.CHECK;
            return true;
        }

        else return false;
    }

    public boolean cook () {
        if(this.state == States.COOK) {
            System.out.println("Cooking");
            System.out.println("Your change is " + getChange());
            finish();
            return true;
        }

        return false;

    }

    private void finish () {
        if (this.state == States.COOK) {
            this.state = States.WAIT;
        }
    }

    public int getChange () {
        int change = this.cash;
        this.cash = 0;
        return change;
    }

    public void printAll () {
        System.out.println ("Cash =" + cash);
        System.out.println ("State =" + getState());
    }



}

public class AutomataDemo {
    public static void main (String args []) {
        Automata nescafe = new Automata();

        nescafe.on();
        System.out.println(nescafe.getMenu());

        nescafe.cook();
        nescafe.off();


        nescafe.printAll();

    }
}
