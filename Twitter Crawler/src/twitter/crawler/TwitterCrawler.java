/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter.crawler;

/**
 *
 * @author ashleyseong
 */
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.*;
import java.util.List;

    public class TwitterCrawler
    {
        public static void main(String[] args)
        {
            try
            {
                // Authorise the library
                ConfigurationBuilder cb = new ConfigurationBuilder();
                cb.setOAuthConsumerKey("AhoydO8uSe4v8NEq7j2ISGFlq");
                cb.setOAuthConsumerSecret("ptKEYwq3G9vpFkqAhvwFLSWFcBW8U1SfqycECwK4cH6wThVba6");
                cb.setOAuthAccessToken("778240255577194496-taafqDIHebrg972oxT5kTqcNd3Uojod");
                cb.setOAuthAccessTokenSecret("DMRmeRahnLJRvCBIGQGTaTzE6Pr3PAZMgMsfWIT5ue3PD");
                Twitter twitter = new TwitterFactory(cb.build()).getInstance();
                User user = twitter.verifyCredentials(); // Get main user
                
                long cursor = -1;

                // Print user profile
                System.out.println("@" + user.getScreenName());
                System.out.println(user.getId());
                System.out.println(user.getProfileImageURL());
                System.out.println(user.getFriendsCount() + " friends.");
                System.out.println("-------");

                // Print Home Timeline
                List<Status> statuses = twitter.getHomeTimeline();
                System.out.println("Showing @" + user.getScreenName() + "'s home timeline.");

                for (Status status : statuses)
                {
                    System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                }
                
                //Print followers
                System.out.println("-------");
                System.out.println("Showing Follwers:");
                
                PagableResponseList<User> followers;
                
                //do
                //{
                    followers = twitter.getFollowersList(user.getScreenName(), cursor);
                    
                    for (User follower : followers)
                    {
                        System.out.println("@" + follower.getScreenName());
                    }
                //}
                //while ((cursor = followers.getNextCursor())!=-1);
                        
                //Print follwees

                System.out.println("-------");
                System.out.println("Showing Followees:");
                
                PagableResponseList<User> followees;
                        
                do
                {
                    followees = twitter.getFriendsList(user.getScreenName(), cursor);
                
                
                    for (User followee : followees)
                    {
                        System.out.println("@" + followee.getScreenName());
                    }
                }
                while ((cursor = followees.getNextCursor())!=-1);
            }
                
            catch (TwitterException te)
            {
                te.printStackTrace();
                System.out.println("Failed to get timeline: " + te.getMessage());
                System.exit(-1);
            }
        }
}
