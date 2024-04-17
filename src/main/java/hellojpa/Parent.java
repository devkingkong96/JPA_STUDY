package hellojpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true) // cascade = CascadeType.ALL : 전부 persist 날려준다는 의미, orphanRemoval = true : 자식 엔티티를 컬렉션에서 제거
    private List<Child> childList = new ArrayList<>();

    public void addChild(Child child){
        childList.add(child);
        child.setParent(this);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }
}
