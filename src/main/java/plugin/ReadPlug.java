package plugin;

import com.jihuayu.core.api.Plugin;

import java.util.List;

public class ReadPlug implements Plugin {
    @Override
    public void getCommandUsage(List<String> info) {
        info.add("hello");
    }

    @Override
    public String getCommandName() {
        return "r";
    }

    @Override
    public void loadCommand(String[]args) {
        for(String i :args){
            System.out.println(i);
        }
    }
}
