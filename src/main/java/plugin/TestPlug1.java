package plugin;

import com.jihuayu.core.api.Plugin;

import java.util.List;

public class TestPlug1 implements Plugin {
    @Override
    public void getCommandUsage(List<String> info) {
        info.add("this is a test");
    }

    @Override
    public String getCommandName() {
        return "test";
    }

    @Override
    public void loadCommand(String[]args) {
        System.out.println(101011);
    }
}
