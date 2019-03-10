package plugin;

import com.jihuayu.core.api.Plugin;

import java.util.List;

public class ReadPlug implements Plugin {
    public String in;
    @Override
    public void getCommandUsage(List<String> info) {
        info.add("<file>:read from file");
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
