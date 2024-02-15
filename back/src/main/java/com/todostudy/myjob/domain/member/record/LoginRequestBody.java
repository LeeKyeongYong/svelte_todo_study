package com.todostudy.myjob.domain.member.record;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestBody(@NotBlank String username, @NotBlank String password) { }
