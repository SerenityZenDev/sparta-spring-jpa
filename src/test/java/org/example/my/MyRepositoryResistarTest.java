package org.example.my;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(MyRepositoryRegistar.class)
@SpringBootTest
public class MyRepositoryResistarTest {

    @Autowired
    private MyRepository myRepository;

    @Test
    void myRepositoryResistarTest() {
        // given
        var newData = "NEW DATA";
        var savedId = myRepository.save(newData);

        // when
        var newDataList = myRepository.find(savedId);

        // then
        System.out.println(newDataList);
    }
}
