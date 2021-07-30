package com.basis.campina.xtarefas.service.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponsavelEvent extends BaseEvent{
    public ResponsavelEvent(Long id){
        super(id);
    }
}
