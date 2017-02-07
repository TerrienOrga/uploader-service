package com.ote.file;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class RowTest {

    @Test
    public void fileRowShouldBeMappedIntoRowObject(){

        String line = "toto tata 15642";
        IFileIntegrationService service = (file, row) -> System.out.println("Dummy");
        Row row = service.toRow(line);
        Assertions.assertThat(row.getNodeLeft()).isEqualTo("toto");
        Assertions.assertThat(row.getNodeRight()).isEqualTo("tata");
        Assertions.assertThat(row.getDistance()).isEqualTo(15642);
    }
}
