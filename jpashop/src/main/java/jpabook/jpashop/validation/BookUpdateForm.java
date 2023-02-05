package jpabook.jpashop.validation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BookUpdateForm {
    @NotBlank
    private String name;
    @NotNull
    private int price;
    @NotNull
    private int stockQuantity;
}
