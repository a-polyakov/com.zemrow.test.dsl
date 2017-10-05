package com.zemrow.test.dsl.querydsl.dao.autogen.entity;

import com.zemrow.test.dsl.querydsl.dao.AbstractEntity;

/**
 * Класс сгенегирован автоматически, для таблицы Erout_log из БД
 */
public class Erout_log extends AbstractEntity {

    public String token;

    public String service_id;

    public String action;

    public Long start_invoke;

    public Long end_invoke;

    public String error_stack_trace;

    public Erout_log() {
    }

    public Erout_log(java.util.UUID id, String token, String service_id, String action, Long start_invoke, Long end_invoke, String error_stack_trace) {
        super(id);
        this.token = token;
        this.service_id = service_id;
        this.action = action;
        this.start_invoke = start_invoke;
        this.end_invoke = end_invoke;
        this.error_stack_trace = error_stack_trace;
    }

}

