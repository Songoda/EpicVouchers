package com.songoda.epicvouchers.commands;

import com.songoda.core.commands.AbstractCommand;
import com.songoda.epicvouchers.EpicVouchers;
import com.songoda.epicvouchers.voucher.Voucher;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CommandGiveAll extends AbstractCommand {
    final EpicVouchers instance;

    public CommandGiveAll(EpicVouchers instance) {
        super(false, "giveall");
        this.instance = instance;
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        if (args.length != 2)
            return ReturnType.SYNTAX_ERROR;

        Voucher voucher = instance.getVoucherManager().getVoucher(args[0]);
        if (voucher == null) {
            sender.sendMessage("Unknown voucher...");
            return ReturnType.FAILURE;
        }

        voucher.giveAll(sender, Integer.parseInt(args[1]));
        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(CommandSender sender, String... args) {
        List<String> result = new ArrayList<>();

        if (args.length == 1) {
            for (Voucher voucher : instance.getVoucherManager().getVouchers()) {
                result.add(voucher.getKey());
            }
        } else if (args.length == 2) {
            for (int i = 0; i < 10; ++i) {
                result.add(String.valueOf(i + 1));
            }
        }

        return result;
    }

    @Override
    public String getPermissionNode() {
        return "epicvouchers.admin";
    }

    @Override
    public String getSyntax() {
        return "/ev giveall <voucher> <amount>";
    }

    @Override
    public String getDescription() {
        return "Give everyone online a voucher.";
    }
}
