package com.github.chacha89.todos.dashboard.service;

import com.github.chacha89.todos.common.commonEnum.Priority;
import com.github.chacha89.todos.common.commonEnum.Progress;
import com.github.chacha89.todos.todo.entity.Todo;
import com.github.chacha89.todos.todo.exception.TodoCreateException;
import com.github.chacha89.todos.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DashBoardService {
    private final TodoRepository todoRepository;

    public DashBoardService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    /**
     * 대시보드(총 개수 구하기)
     */
    public Long getTodoAllCountAPI(){
        return todoRepository.countByIsDeletedFalse();
    }

    /**
     * 대시보드 - 특정 상태 개수 구하기
     */
    public Long getProgressCount(String progress){
        try {
            Progress countProgress = Progress.valueOf(progress.trim().toUpperCase());
            return todoRepository.countByProgress(countProgress);
        } catch(IllegalArgumentException e){
            throw new TodoCreateException(400, "존재하지 않는 상태 값입니다.");
        }
    }

    /**
     *  대시보드 완성율
     */
    public double getProgressPercent(){
        Long totalNumber = todoRepository.countByIsDeletedFalse();
        Long doneNumber = todoRepository.countByProgress(Progress.DONE);
        double result = (double) doneNumber / totalNumber * 100;
        return result;
    }

    /**
     * 대시보드 태스크 요약
     */
    public Map<Priority, List<Todo>> getTodoSummary(){

        List<Todo> todos = todoRepository.findAll();

        // 우선순위 Enum 배열을 내림차순 정렬
        Priority[] priorities = Priority.values();
        Arrays.sort(priorities, (a, b) -> b.getLevel() - a.getLevel());

        Map<Priority, List<Todo>> groupedMap = new LinkedHashMap<>();

        for (Priority priority : priorities) {
            List<Todo> filtered = new ArrayList<>();

            for (Todo todo : todos) {
                if (todo.getPriority() == priority) {
                    filtered.add(todo);
                }
            }

            // 마감일 오름차순 정렬
            filtered.sort(Comparator.comparing(Todo::getDueDate));
            groupedMap.put(priority, filtered);
        }

        return groupedMap;
    }
}
