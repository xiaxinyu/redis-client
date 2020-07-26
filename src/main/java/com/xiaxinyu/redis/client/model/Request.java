package com.xiaxinyu.redis.client.model;

import lombok.*;

import java.util.Objects;

/**
 * @author summer
 * @date 2020.7.26
 */
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    String id;
    String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Request request = (Request) o;
        return Objects.equals(id, request.id) &&
                Objects.equals(content, request.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content);
    }
}
