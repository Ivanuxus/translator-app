
package com.example.translator.repository;

import com.example.translator.model.TranslationEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TranslationRepository {

    private final JdbcTemplate jdbcTemplate;

    public TranslationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(TranslationEntity entity) {
        String sql = "INSERT INTO translations (ip_address, input_text, translated_text, timestamp) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, entity.getIpAddress(), entity.getInputText(), entity.getTranslatedText(), entity.getTimestamp());
    }

}
