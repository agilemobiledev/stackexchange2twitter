package org.tweet.stackexchange.persistence.setup;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
public class SetupBackupIntegrationTest {

    class TweetRowMapper implements RowMapper<String> {
        private final Map<String, List<Long>> accountToQuestions;

        public TweetRowMapper(final Map<String, List<Long>> accountToQuestions) {
            super();
            this.accountToQuestions = accountToQuestions;
        }

        @Override
        public final String mapRow(final ResultSet rs, final int line) throws SQLException {
            final String questionIdAsString = rs.getString("question_id");
            final long questionId = Long.parseLong(questionIdAsString);
            final String account = rs.getString("account");

            if (accountToQuestions.get(account) == null) {
                accountToQuestions.put(account, Lists.<Long> newArrayList());
            }
            accountToQuestions.get(account).add(questionId);
            return "";
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
        final Map<String, List<Long>> accountToQuestionsMaps = Maps.newHashMap();
        jdbcTemplate.query("SELECT * FROM question_tweet;", new TweetRowMapper(accountToQuestionsMaps));

        writeToFile(accountToQuestionsMaps);
    }

    // util

    private void writeToFile(final Map<String, List<Long>> accountToQuestionsMaps) {
        //
    }

}