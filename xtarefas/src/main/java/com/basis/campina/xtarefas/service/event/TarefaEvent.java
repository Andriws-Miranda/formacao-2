package com.basis.campina.xtarefas.service.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TarefaEvent extends BaseEvent {
    public TarefaEvent(Long id){
        super(id);
    }
}
