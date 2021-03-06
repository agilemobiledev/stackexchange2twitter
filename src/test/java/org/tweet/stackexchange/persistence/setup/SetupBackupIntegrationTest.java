package org.tweet.stackexchange.persistence.setup;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tweet.spring.SetupPersistenceTestConfig;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SetupPersistenceTestConfig.class })
@Ignore
public class SetupBackupIntegrationTest {

    class TweetRowMapper implements RowMapper<String> {
        private final Map<String, List<Long>> accountToQuestions;
        private int count;

        public TweetRowMapper(final Map<String, List<Long>> accountToQuestions) {
            super();
            count = 0;
            this.accountToQuestions = accountToQuestions;
        }

        @Override
        public final String mapRow(final ResultSet rs, final int line) throws SQLException {
            final String questionIdAsString = rs.getString("question_id");
            final long questionId = Long.parseLong(questionIdAsString);
            final String account = rs.getString("twitter_account");

            if (accountToQuestions.get(account) == null) {
                accountToQuestions.put(account, Lists.<Long> newArrayList());
            }
            accountToQuestions.get(account).add(questionId);
            count++;
            return "";
        }

        public final int getCount() {
            return count;
        }
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // fixtures

    // tests

    @Test
    public final void whenSetupContextIsBootstrapped_thenNoExceptions() {
        //
    }

    @Test
    public final void whenQuestionsAreRetrievedFromTheDB_thenNoExceptions() {
        final Map<String, List<Long>> accountToQuestionsMap = Maps.newHashMap();
        final TweetRowMapper tweetRowMapper = new TweetRowMapper(accountToQuestionsMap);
        jdbcTemplate.query("SELECT * FROM question_tweet;", tweetRowMapper);

        listOut(accountToQuestionsMap);
        System.out.println("Processed: " + tweetRowMapper.getCount());
    }

    // util

    private void listOut(final Map<String, List<Long>> accountToQuestionsMap) {
        for (final String accountName : accountToQuestionsMap.keySet()) {
            System.out.println(accountName + "=" + valuesAsCsv(accountToQuestionsMap.get(accountName)));
        }
    }

    private String valuesAsCsv(final List<Long> theIds) {
        final StringBuilder allQuestionsAsString = new StringBuilder();
        for (final Long id : theIds) {
            allQuestionsAsString.append(id);
            allQuestionsAsString.append(',');
        }

        allQuestionsAsString.deleteCharAt(allQuestionsAsString.length() - 1);

        return allQuestionsAsString.toString();
    }

}
