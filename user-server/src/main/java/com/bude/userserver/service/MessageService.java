package com.bude.userserver.service;

import com.bude.userserver.entity.MessageEntity;
import com.bude.utils.model.Message;
import com.bude.utils.result.Result;

import java.util.List;

public interface MessageService {

    Result<Boolean> sendMessage(MessageEntity messageEntity);

    Result<List<Message>> getMessage(int myId);
}
