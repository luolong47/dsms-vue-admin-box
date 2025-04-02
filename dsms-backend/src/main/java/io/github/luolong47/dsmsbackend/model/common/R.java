package io.github.luolong47.dsmsbackend.model.common;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class R<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> R<T> ok(T data) {
        return new R<T>()
                .setCode(200)
                .setMsg("success")
                .setData(data);
    }

    public static <T> R<T> fail(String msg) {
        return new R<T>()
                .setCode(500)
                .setMsg(msg);
    }

    public static R<Void> ok() {
        return new R<Void>()
            .setCode(200)
            .setMsg("success");
    }
}
