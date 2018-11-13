package gerrix90.expanding.collection.comments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import java.util.List;
import java.util.Random;

import gerrix90.expanding.collection.R;
import gerrix90.expanding.collection.comments.pojo.CardData;
import gerrix90.expanding.collection.comments.pojo.Comment;
import gerrix90.expanding.collection.explib.ECCardData;


public class ExampleDataset {

    private List<ECCardData> dataset;
    private  String urlImage = "http://i.imgur.com/DvpvklR.png";
    private  String urlImage1 = "https://images.unsplash.com/photo-1505282722405-413748d3de7a?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=ffff11fbee3647471e3b9dadcd7773a4&auto=format&fit=crop&w=701&q=80";
    private  String urlImage2 = "https://images.unsplash.com/photo-1485579149621-3123dd979885?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=bdad9431efc1a4481ac8a264d7c33a61&auto=format&fit=crop&w=1789&q=80";
    private  String urlImage3 = "https://images.unsplash.com/photo-1526343110727-c924bf71c166?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=da383690689604e155a7a54424405b8c&auto=format&fit=crop&w=500&q=60";
    private  String urlImage4 = "https://images.unsplash.com/photo-1525908034958-2c082cd0fed2?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=0135edb04ff5fb12fd8434aacc1acf7e&auto=format&fit=crop&w=500&q=60";
    private  String urlImage5 = "https://images.unsplash.com/photo-1526218626217-dc65a29bb444?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=c7f32f1b46027dde5d2c0a8f11f1edf2&auto=format&fit=crop&w=500&q=60";

    public ExampleDataset() {
        dataset = new ArrayList<>(5);

        CardData item5 = new CardData();
        item5.setMainBackgroundResource(R.drawable.attractions);
        item5.setUrlImage(urlImage5);
//        item5.setHeadBackgroundResource(R.drawable.attractions_head);
        item5.setHeadTitle("Zuto");
        item5.setPersonMessage("Usus de bassus buxum, desiderium index!");
        item5.setPersonName("Marjorie Ellis");
        item5.setPersonPictureResource(R.drawable.marjorie_ellis);
        item5.setListItems(prepareCommentsArray());
        dataset.add(item5);

        CardData item4 = new CardData();
        item4.setMainBackgroundResource(R.drawable.city_scape);
        item4.setUrlImage(urlImage4);

//        item4.setHeadBackgroundResource(R.drawable.city_scape_head);
        item4.setHeadTitle("Siva");
        item4.setPersonMessage("Solems manducare, tanquam neuter verpa.");
        item4.setPersonName("Mattew Jordan");
        item4.setPersonPictureResource(R.drawable.mattew_jordan);
        item4.setListItems(prepareCommentsArray());
        dataset.add(item4);

        CardData item3 = new CardData();
        item3.setMainBackgroundResource(R.drawable.cuisine);
        item3.setUrlImage(urlImage3);

//        item3.setHeadBackgroundResource(R.drawable.cuisine_head);
        item3.setHeadTitle("Gitara");
        item3.setPersonMessage("Magnum lacteas ducunt ad orexis.");
        item3.setPersonName("Ross Rodriguez");
        item3.setPersonPictureResource(R.drawable.ross_rodriguez);
        item3.setListItems(prepareCommentsArray());
        dataset.add(item3);

        CardData item2 = new CardData();
        item2.setMainBackgroundResource(R.drawable.nature);
        item2.setUrlImage(urlImage2);

//        item2.setHeadBackgroundResource(R.drawable.nature_head);
        item2.setHeadTitle("Mikrofon");
        item2.setPersonName("Tina Caldwell");
        item2.setPersonMessage("Nunquam perdere clabulare.");
        item2.setListItems(prepareCommentsArray());
        item2.setPersonPictureResource(R.drawable.tina_caldwell);
        dataset.add(item2);

        CardData item1 = new CardData();
        item1.setMainBackgroundResource(R.drawable.night_life);
        item1.setUrlImage(urlImage1);

//        item1.setHeadBackgroundResource(R.drawable.night_life_head);
        item1.setHeadTitle("Night Life");
        item1.setPersonMessage("Cur adelphis studere?");
        item1.setPersonName("Wallace Sutton");
        item1.setPersonPictureResource(R.drawable.wallace_sutton);
        item1.setListItems(prepareCommentsArray());
        dataset.add(item1);

    }

    public List<ECCardData> getDataset() {
//        Collections.shuffle(dataset);
        return dataset;
    }

    private List<Comment> prepareCommentsArray() {
        Random random = new Random();
        List<Comment> comments = new ArrayList<>();
        comments.addAll(Arrays.asList(
                new Comment(R.drawable.aaron_bradley, "Aaron Bradley", "When the sensor experiments for deep space, all mermaids accelerate mysterious, vital moons.", "jan 12, 2014"),
                new Comment(R.drawable.barry_allen, "Barry Allen", "It is a cold powerdrain, sir.", "jun 1, 2015"),
                new Comment(R.drawable.bella_holmes, "Bella Holmes", "Particle of a calm shield, control the alignment!", "sep 21, 1937"),
                new Comment(R.drawable.caroline_shaw, "Caroline Shaw", "The human kahless quickly promises the phenomenan.", "may 23, 1967"),
                new Comment(R.drawable.connor_graham, "Connor Graham", "Ionic cannon at the infinity room was the sensor of voyage, imitated to a dead pathway.", "sep 1, 1972"),
                new Comment(R.drawable.deann_hunt, "Deann Hunt", "Vital particles, to the port.", "aug 13, 1995"),
                new Comment(R.drawable.ella_cole, "Ella Cole", "Stars fly with hypnosis at the boldly infinity room!", "nov 18, 1952"),
                new Comment(R.drawable.jayden_shaw, "Jayden Shaw", "Hypnosis, definition, and powerdrain.", "apr 1, 2013"),
                new Comment(R.drawable.jerry_carrol, "Jerry Carrol", "When the queen experiments for nowhere, all particles control reliable, cold captains.", "nov 14, 1964"),
                new Comment(R.drawable.lena_lucas, "Lena Lukas", "When the c-beam experiments for astral city, all cosmonauts acquire remarkable, virtual lieutenant commanders.", "may 4, 1965"),
                new Comment(R.drawable.leonrd_kim, "Leonard Kim", "Starships walk with love at the cold parallel universe!", "jul 3, 1974"),
                new Comment(R.drawable.marc_baker, "Mark Baker", "Friendship at the bridge that is when quirky green people yell.", "dec 24, 1989")));
        Collections.shuffle(comments);
        return comments.subList(0, 6 + random.nextInt(5));
    }
}
