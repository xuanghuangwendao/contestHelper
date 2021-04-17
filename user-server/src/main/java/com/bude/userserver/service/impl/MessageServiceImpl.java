package com.bude.userserver.service.impl;

import com.bude.userserver.entity.MessageEntity;
import com.bude.userserver.repository.MessageRepository;
import com.bude.userserver.service.MessageService;
import com.bude.userserver.service.UserService;
import com.bude.userserver.result.Result;
import com.bude.userserver.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserService userService;


    @Override
    public Result<Boolean> sendMessage(MessageEntity messageEntity) {

        MessageEntity entity = messageRepository.save(messageEntity);
        return new Result<>(true);
    }

    @Override
    public Result<List<Message>> getMessage(int myId) {
        List<MessageEntity> list = messageRepository.findAllByAidEqualsOrSidEquals(myId, myId);
        Result<List<Message>> result = new Result<>();
        List<Message> messages = new ArrayList<>();
        list.sort(Comparator.comparing(MessageEntity::getTime).reversed());
        for (MessageEntity entity: list) {
            Message message = new Message();
            message.setId(entity.getId());
            message.setAid(entity.getAid());
            message.setAccepterName(userService.getName(entity.getAid()).data);
            message.setSenderName(userService.getName(entity.getSid()).data);
            message.setSid(entity.getSid());
            message.setContent(entity.getContent());
            message.setType(entity.getType());
            message.setTime(new SimpleDateFormat("yyyy-MM-dd").format(entity.getTime()));
            messages.add(message);
        }
        result.setData(messages);
        return result;
    }
}
