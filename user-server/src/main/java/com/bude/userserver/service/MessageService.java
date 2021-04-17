package com.bude.userserver.service;

import com.bude.userserver.entity.MessageEntity;
import com.bude.userserver.result.Result;
import com.bude.userserver.model.*;
import java.util.List;

public interface MessageService {

    Result<Boolean> sendMessage(MessageEntity messageEntity);

    Result<List<Message>> getMessage(int myId);
}
