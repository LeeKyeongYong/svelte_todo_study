package com.todostudy.myjob.domain.member.record;

import com.todostudy.myjob.domain.member.dto.MemberDto;
import lombok.NonNull;

public record MeResponseBody(@NonNull MemberDto item) { }
