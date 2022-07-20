package jpabook.jpashop.service;


import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}

// 사실상 레포지토리를 위임한 느낌으로 개발한다. 앞서 서비스에서 전부 만들어진 내용을 갖다 쓴 느낌이기 떄문이다.
// 굳이 만들어야 하는 고민이 생긴다면 뭐 컨트롤러에서 바로 레포지토리에 접근하는 방식으로 프로세스를 짜서 안만들어도 된다.
