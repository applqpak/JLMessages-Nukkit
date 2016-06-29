package applqpak.JLMessages;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.event.Listener;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.utils.TextFormat;
import cn.nukkit.utils.Config;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.Command;
import cn.nukkit.Player;

import java.io.File;

public class Main extends PluginBase implements Listener
{

  public String VERSION = "v1.0.0";

  public String USAGE = "/jlmessages <version | set> [join | leave] [message]";

  public String implode(String glue, String[] strArray)
  {

    String ret = "";

    for(int i = 0; i < strArray.length; i++)
    {

      if(strArray[i].trim() != "")
      {

        ret += (i == strArray.length - 1) ? strArray[i] : strArray[i] + glue;

      }

    }

    return ret;

  }

  @Override

  public void onEnable()
  {

    this.getDataFolder().mkdirs();

    if(!(new File(this.getDataFolder(), "config.yml").exists()))
    {

      saveResource("config.yml");

    }

    this.getServer().getPluginManager().registerEvents(this, this);

    this.getLogger().info(TextFormat.GREEN + "Enabled.");

  }

  @Override

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {

    switch(cmd.getName())
    {

      case "jlmessages":

        if(args.length == 0)
        {

          sender.sendMessage(TextFormat.RED + "Invalid usage. Usage: " this.USAGE);

        }
        else
        {

          if(args[0].equalsIgnoreCase("version"))
          {

            sender.sendMessage(TextFormat.YELLOW + "-- JLMessages version --");

            sender.sendMessage(TextFormat.GREEN + this.VERSION);

          }
          else if(args[0].equalsIgnoreCase("set"))
          {

            if(args.length == 1)
            {

              sender.sendMessage(TextFormat.RED + "Invalid usage. Usage: " + this.USAGE);

            }
            else if(args[1].equalsIgnoreCase("join"))
            {

              if(args.length == 2)
              {

                sender.sendMessage(TextFormat.RED + "Invalid usage. Usage: " + this.USAGE);

              }
              else
              {

                args[0] = "";

                args[1] = "";

                String message = this.implode(" ", args);

                getConfig().set("join_message", message);

                getConfig().save();

                sender.sendMessage(TextFormat.GREEN + "Successfully set the join message!");

              }

            }

          }

        }

      break;

    }

    return true;

  }

  @Override

  public void onDisable()
  {

    this.getLogger().info(TextFormat.RED + "Disabled.");

  }

}
