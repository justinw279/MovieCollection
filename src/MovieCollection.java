import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class MovieCollection {

    private ArrayList<Movie> movies = new ArrayList<Movie>();

    public MovieCollection() {
        importMovies();
    }

    public void menu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scan.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }
        scan.close();
    }

    private void searchTitles() {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> titles = new ArrayList<>();
        System.out.print("Enter a title search term: ");
        String title = scan.nextLine();
        title = title.toLowerCase();

        for (Movie m: movies) {
            if (m.getTitle().toLowerCase().indexOf(title) != -1) {
                titles.add(m.getTitle());
            }
        }

        selectionSortWordList(titles);
        //DEBUG ONLY
        System.out.println(titles);

        if (titles.size() == 0) {
            System.out.println("No movie titles match that search term!");
        } else {
            for (int i = 0; i < titles.size(); i++) {
                System.out.println((i + 1) + ". " + titles.get(i));
            }
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");
        int choice = scan.nextInt();
        scan.nextLine();

        for (Movie m: movies) {
            if (m.getTitle().equals(titles.get(choice - 1))) {
                System.out.println(m);
            }
        }
    }

    private void searchCast() {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> cast = new ArrayList<>();

        ArrayList<String> moviesFeaturing = new ArrayList<>();

        System.out.print("Enter a cast member: ");
        String castMember = scan.nextLine();
        castMember = castMember.toLowerCase();

        for (Movie m : movies) {
            if (m.getCast().toLowerCase().indexOf(castMember) != -1) {
                String[] movieCast = m.getCast().split("\\|");
                for (int i = 0; i < movieCast.length; i++) {
                    if (movieCast[i].toLowerCase().indexOf(castMember) != -1) {
                        cast.add(movieCast[i]);
                    }
                }
            }
        }

        for (int i = 0; i < cast.size() - 1; i++) {
            for (int j = i + 1; j < cast.size(); j++) {
                if (cast.get(i).equals(cast.get(j))) {
                    cast.remove(j);
                    j--;
                }
            }
        }

        if (cast.size() == 0) {
            System.out.println("No cast members match that search term!");
        } else {
            selectionSortWordList(cast);
            for (int i = 0; i < cast.size() - 1; i++) {
                System.out.println((i + 1) + ". " + cast.get(i));
            }
        }

        System.out.println("Which would you like to see all movies for?");
        System.out.print("Enter a number: ");
        int choice = scan.nextInt();
        scan.nextLine();

        for (Movie m : movies) {
            if (m.getCast().indexOf(cast.get(choice - 1)) != -1) {
                moviesFeaturing.add(m.getTitle());
            }
        }

        selectionSortWordList(moviesFeaturing);
        for (int i = 0; i < moviesFeaturing.size(); i++) {
            System.out.println((i + 1) + ". " + moviesFeaturing.get(i));
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");
        int option = scan.nextInt();
        scan.nextLine();

        for (Movie m: movies) {
            if (m.getTitle().equals(moviesFeaturing.get(option - 1))) {
                System.out.println(m);
            }
        }
    }

    private void selectionSortWordList(ArrayList<String> words) {
        for (int i = 0; i < words.size() - 1; i++) {
            int min = i;
            String holder = words.get(i);


            for (int j = i; j < words.size(); j++) {
                if (words.get(j).compareTo(holder) < 0) {
                    min = j;
                    holder = words.get(j);
                }
            }


            words.set(min, words.get(i));
            words.set(i, holder);
        }
    }

    private void importMovies(){
        Scanner scan = new Scanner(System.in);
        File f = new File("src\\movies_data.csv");
        try {
            scan = new Scanner(f);
            scan.nextLine();
            while (scan.hasNextLine()) {
                String data = scan.nextLine();
                String[] movieArray = data.split(",");

                String name = movieArray[0];
                String cast = movieArray[1];
                String director = movieArray[2];
                String overview = movieArray[3];
                int runtime = Integer.parseInt(movieArray[4]);
                double userRating = Double.parseDouble(movieArray[5]);

                Movie m = new Movie(name, cast, director, overview, runtime, userRating);
                movies.add(m);

            }
        } catch (FileNotFoundException e) {
            System.exit(123);
        }
    }


}
