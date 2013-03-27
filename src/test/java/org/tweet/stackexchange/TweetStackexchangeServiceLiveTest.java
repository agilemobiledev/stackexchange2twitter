package org.tweet.stackexchange;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.stackexchange.api.constants.Site;
import org.tweet.spring.ContextConfig;
import org.tweet.spring.PersistenceJPAConfig;
import org.tweet.spring.StackexchangeConfig;
import org.tweet.spring.TwitterConfig;
import org.tweet.stackexchange.util.SimpleTwitterAccount;
import org.tweet.stackexchange.util.StackexchangeUtil;
import org.tweet.stackexchange.util.Tag;

import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TwitterConfig.class, ContextConfig.class, PersistenceJPAConfig.class, StackexchangeConfig.class })
public class TweetStackexchangeServiceLiveTest {

    @Autowired
    private TweetStackexchangeService tweetStackexchangeService;

    // tests

    @Test
    public final void whenTweeting_thenNoExceptions() throws JsonProcessingException, IOException {
        tweetStackexchangeService.tweetTopQuestionBySite(Site.stackoverflow, SimpleTwitterAccount.SpringAtSO.name(), 1);
    }

    @Test
    public final void whenTweetingByTag_thenNoExceptions() throws JsonProcessingException, IOException {
        tweetStackexchangeService.tweetTopQuestionByTag(Site.stackoverflow, SimpleTwitterAccount.SpringAtSO.name(), Tag.spring.name(), 1);
    }

    @Test
    public final void whenTweetingByTagJava_thenNoExceptions() throws JsonProcessingException, IOException {
        tweetStackexchangeService.tweetTopQuestionByTag(Site.stackoverflow, SimpleTwitterAccount.JavaTopSO.name(), Tag.java.name(), 1);
    }

    @Test
    public final void whenTweetingByTagClojure_thenNoExceptions() throws JsonProcessingException, IOException {
        tweetStackexchangeService.tweetTopQuestionByTag(Site.stackoverflow, SimpleTwitterAccount.BestClojure.name(), Tag.clojure.name(), 1);
    }

    @Test
    public final void whenTweetingByTagScala_thenNoExceptions() throws JsonProcessingException, IOException {
        tweetStackexchangeService.tweetTopQuestionByTag(Site.stackoverflow, SimpleTwitterAccount.BestScala.name(), Tag.scala.name(), 1);
    }

    @Test
    public final void whenTweetingByTagJquery_thenNoExceptions() throws JsonProcessingException, IOException {
        tweetStackexchangeService.tweetTopQuestionByTag(Site.stackoverflow, SimpleTwitterAccount.jQueryDaily.name(), Tag.jquery.name(), 1);
    }

    @Test
    public final void whenTweetingByTagREST_thenNoExceptions() throws JsonProcessingException, IOException {
        tweetStackexchangeService.tweetTopQuestionByTag(Site.stackoverflow, SimpleTwitterAccount.RESTDaily.name(), Tag.rest.name(), 1);
    }

    @Test
    public final void whenTweetingByTagEclipse_thenNoExceptions() throws JsonProcessingException, IOException {
        tweetStackexchangeService.tweetTopQuestionByTag(Site.stackoverflow, SimpleTwitterAccount.BestEclipse.name(), Tag.eclipse.name(), 1);
    }

    @Test
    public final void whenTweetingByTagGit_thenNoExceptions() throws JsonProcessingException, IOException {
        tweetStackexchangeService.tweetTopQuestionByTag(Site.stackoverflow, SimpleTwitterAccount.BestGit.name(), Tag.git.name(), 1);
    }

    @Test
    public final void whenTweetingByTagMaven_thenNoExceptions() throws JsonProcessingException, IOException {
        tweetStackexchangeService.tweetTopQuestionByTag(Site.stackoverflow, SimpleTwitterAccount.BestMaven.name(), Tag.maven.name(), 1);
    }

    @Test
    public final void whenTweetingByTagJPA_thenNoExceptions() throws JsonProcessingException, IOException {
        tweetStackexchangeService.tweetTopQuestionByTag(Site.stackoverflow, SimpleTwitterAccount.BestJPA.name(), Tag.jpa.name(), 1);
    }

    @Test
    public final void whenTweetingByTagAlgorithm_thenNoExceptions() throws JsonProcessingException, IOException {
        tweetStackexchangeService.tweetTopQuestionByTag(Site.stackoverflow, SimpleTwitterAccount.BestAlgorithms.name(), Tag.algorithm.name(), 1);
    }

    @Test
    public final void whenTweetingByRandomTag_thenNoExceptions() throws JsonProcessingException, IOException {
        final Site randomSite = StackexchangeUtil.pickOne(Site.stackoverflow, Site.askubuntu, Site.superuser);
        tweetStackexchangeService.tweetTopQuestionByTag(randomSite, SimpleTwitterAccount.BestBash.name(), Tag.bash.name(), 1);
    }

    // list tweets

    @Test
    public final void whenListingTweets_thenNoExceptions() throws JsonProcessingException, IOException {
        System.out.println(tweetStackexchangeService.listTweets(SimpleTwitterAccount.BestJPA.name()));
    }

}
