package com.ote.file;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RowTest.class)
public class RowTest {

    @Autowired
    private IFileIntegrationService fileIntegrationService;

    @Bean
    public IFileIntegrationService fileIntegrationService(){
        return new IFileIntegrationService() {
            @Override
            public void persist(String fileName, Row row) {

            }
        };
    }

    @Test
    public void fileRowShouldBeMappedIntoRowObject(){

        String line = "toto tata 15642";
        Row row = fileIntegrationService.toRow(line);
        Assertions.assertThat(row.getNodeLeft()).isEqualTo("toto");
        Assertions.assertThat(row.getNodeRight()).isEqualTo("tata");
        Assertions.assertThat(row.getDistance()).isEqualTo(15642);
    }
}
