package org.example.thread;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.RepositoryDefinition;


public interface ThreadRepositoryQuery {

    Page<Thread> search(ThreadSearchCond cond, Pageable pageable);

}
