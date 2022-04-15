package jp.gihyo.projava.tasklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
public class HomeRestHello {
    private final TaskListDao dao;

    @Autowired
    public HomeRestHello(TaskListDao dao) {
        this.dao = dao;
    }

    public record TaskItem(String id, String task, String deadline, boolean done) {
    }
//    private List<TaskItem> taskItems = new ArrayList<TaskItem>();

    @RequestMapping("/home")
    String main(Model model) {
        model.addAttribute("time", LocalDateTime.now());
        return "/home";
    }

    @GetMapping("/add")
    String addLine(@RequestParam("task") String task, @RequestParam("deadline") String deadline) {
        if(!task.isEmpty()){
            String uuid = UUID.randomUUID().toString().substring(0, 8);
            TaskItem taskitem = new TaskItem(uuid, task, deadline, false);
            dao.add(taskitem);
        }
        return "redirect:/list";
    }
//    @GetMapping("/add")
//    String addLine(@RequesredirecttParam("task") String task, @RequestParam("deadline") String deadline) {
//        String uuid = UUID.randomUUID().toString().substring(0, 8);
//        TaskItem taskitem = new TaskItem(uuid, task, deadline, false);
//        taskItems.add(taskitem);
//        return "redirect:/list";
//    }

    @GetMapping("/delete")
    String deleteItem(@RequestParam("id") String id) {
        dao.delete(id);
        return "redirect:/list";
    }

    @GetMapping("/update")
    String updateItem(@RequestParam("id") String id,
                      @RequestParam("task") String task,
                      @RequestParam("deadline") String deadline,
                      @RequestParam("done") boolean done) {
        TaskItem taskItem = new TaskItem(id, task, deadline, done);
        dao.update(taskItem);
        return "redirect:/list";
    }

    @GetMapping("/list")
    String listItems(Model model) {
        List<TaskItem> taskItems = dao.findAll();
//        String items= taskItems.stream().map(TaskItem::toString).collect(Collectors.joining(","));
        model.addAttribute("taskList", taskItems);
        return "/home";
    }
}