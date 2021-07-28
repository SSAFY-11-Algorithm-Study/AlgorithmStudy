import java.util.*;

class Person {
    int idx;
    int weight;
    int height;
    int rank;

    public Person(int idx, int weigth, int height) {
        this.idx = idx;
        this.weight = weigth;
        this.height = height;
        rank = 0;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        LinkedList<Person> list = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            list.add(new Person(i, sc.nextInt(), sc.nextInt()));
        }

        for(int i = 0; i < n; i++) {
            int rank = 1;
            Person p1 = list.get(i);
            for(int j = 0; j < n; j++) {
                if (i != j) {
                    Person p2 = list.get(j);
                    if (p1.weight < p2.weight && p1.height < p2.height) {
                        rank++;
                    }
                }
            }
            p1.rank = rank;
        }

        for (int i = 0; i < n - 1; i++) {
            System.out.print(list.get(i).rank + " ");
        }
        System.out.println(list.get(n - 1).rank);
    }

}
