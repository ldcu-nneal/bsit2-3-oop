import java.util.*;

public class PostManager {

    public int calculateEngagement(int... interactions) {
        if (interactions == null || interactions.length == 0) {
            return 0;
        }

        int total = 0;
        for (int interaction : interactions) {
            total += interaction;
        }
        return total;
    }

    public String getCategoryRating(int engagementScore) {
        if (engagementScore >= 1000) {
            return "Viral";
        } else if (engagementScore >= 500) {
            return "Popular";
        } else if (engagementScore >= 100) {
            return "Good";
        } else if (engagementScore >= 50) {
            return "Low";
        } else {
            return "Poor";
        }
    }

    public void displayPostStats(String postTitle, int engagementScore) {
        System.out.println("Post: " + postTitle);
        System.out.println("Engagement Score: " + engagementScore);
    }

    public void displayPostStats(String postTitle, int engagementScore, String category) {
        System.out.println("Post: " + postTitle);
        System.out.println("Engagement Score: " + engagementScore);
        System.out.println("Category: " + category);
    }

    public ArrayList<String> manageHashtags(String[] hashtags) {
        String[] fixedHashtags = new String[5];

        if (hashtags != null) {
            for (int i = 0; i < Math.min(hashtags.length, 5); i++) {
                fixedHashtags[i] = hashtags[i];
            }
        }

        ArrayList<String> hashtagList = new ArrayList<>(Arrays.asList(fixedHashtags));
        Set<String> uniqueSet = new HashSet<>(hashtagList);

        uniqueSet.remove(null);

        return new ArrayList<>(uniqueSet);
    }

    public LinkedList<String> findTrendingPosts(ArrayList<String> posts, HashMap<String, Integer> postEngagement) {
        LinkedList<String> trendingPosts = new LinkedList<>();

        if (posts != null && postEngagement != null) {
            for (String post : posts) {
                Integer engagement = postEngagement.get(post);
                if (engagement != null && engagement > 500) {
                    trendingPosts.add(post);
                }
            }
        }

        return trendingPosts;
    }

    public LinkedHashSet<String> getUniqueAuthors(String... authors) {
        LinkedHashSet<String> uniqueAuthors = new LinkedHashSet<>();

        if (authors != null) {
            for (String author : authors) {
                if (author != null) {
                    uniqueAuthors.add(author);
                }
            }
        }

        return uniqueAuthors;
    }

    public static void main(String[] args) {
        PostManager manager = new PostManager();

        // pagc-alculate sa engagement score
        int engagementScore = manager.calculateEngagement(150, 75, 25);
        String category = manager.getCategoryRating(engagementScore);

        // display post stats using proper method ovelroading
        System.out.println("=== Social Media Post Manager ===");
        manager.displayPostStats("Java Programming Tips", engagementScore, category);
        System.out.println();

        // manage hashtags
        String[] hashtags = {"#java", "#coding", "#programming", "#java", "#tips"};
        ArrayList<String> uniqueHashtags = manager.manageHashtags(hashtags);
        System.out.println("Unique Hashtags: " + uniqueHashtags);

        // mangita ug trending post
        ArrayList<String> posts = new ArrayList<>();
        posts.add("Advanced Java Tutorial");
        posts.add("Spring Boot Guide");

        HashMap<String, Integer> postEngagement = new HashMap<>();
        postEngagement.put("Advanced Java Tutorial", 750);
        postEngagement.put("Spring Boot Guide", 1200);

        LinkedList<String> trendingPosts = manager.findTrendingPosts(posts, postEngagement);
        System.out.println("Trending Posts: " + trendingPosts);

        // para sa unique authors
        LinkedHashSet<String> uniqueAuthors = manager.getUniqueAuthors("Alice", "Bob", "Charlie");
        System.out.println("Unique Authors: " + uniqueAuthors);
    }
}
