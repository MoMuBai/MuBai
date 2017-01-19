package com.lzw.library.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ShellUtil {

	public static final String COMMAND_SU = "su";
	public static final String COMMAND_SH = "sh";
	public static final String COMMAND_EXIT = "exit\n";
	public static final String COMMAND_LINE_END = "\n";

	/**
	 * 检查是否有Root权限
	 * 
	 * @return
	 */
	public static boolean checkRootPermission() {
		return execCommand("echo root", true, false).result == 0;
	}

	/**
	 * 执行命令,默认返回命令结果消息
	 * 
	 * @param command
	 *            命令
	 * @param isRoot
	 *            是否Root
	 * @return
	 */
	public static CommandResult execCommand(String command, boolean isRoot) {
		return execCommand(new String[] { command }, isRoot, true);
	}

	/**
	 * 执行命令集,默认返回命令结果消息
	 * 
	 * @param commands
	 *            命令集
	 * @param isRoot
	 *            是否Root
	 * @return
	 */
	public static CommandResult execCommand(List<String> commands,
			boolean isRoot) {
		return execCommand(
				commands == null ? null : commands.toArray(new String[] {}),
				isRoot, true);
	}

	/**
	 * 执行命令集,默认返回命令结果消息
	 * 
	 * @param commands
	 *            命令集
	 * @param isRoot
	 *            是否Root
	 * @return
	 */
	public static CommandResult execCommand(String[] commands, boolean isRoot) {
		return execCommand(commands, isRoot, true);
	}

	/**
	 * 执行命令
	 * 
	 * @param command
	 *            命令
	 * @param isRoot
	 *            是否Root
	 * @param isNeedResultMsg
	 *            是否需要返回命令结果消息
	 * @return
	 */
	public static CommandResult execCommand(String command, boolean isRoot,
						boolean isNeedResultMsg) {
		return execCommand(new String[] { command }, isRoot, isNeedResultMsg);
	}

	/**
	 * 执行命令集,默认返回命令结果消息
	 * 
	 * @param commands
	 *            命令集
	 * @param isRoot
	 *            是否Root
	 * @param isNeedResultMsg
	 *            是否需要返回命令结果消息
	 * @return
	 */
	public static CommandResult execCommand(List<String> commands,
			boolean isRoot, boolean isNeedResultMsg) {
		return execCommand(
				commands == null ? null : commands.toArray(new String[] {}),
				isRoot, isNeedResultMsg);
	}

	/**
	 * 执行Shell命令
	 * 
	 * @param commands
	 *            命令集数组
	 * @param isRoot
	 *            是否Root
	 * @param isNeedResultMsg
	 *            是否需要返回命令结果消息
	 * @return
	 */
	public static CommandResult execCommand(String[] commands, boolean isRoot,
						boolean isNeedResultMsg) {
		int result = -1;
		if (commands == null || commands.length == 0) {
			return new CommandResult(result, null, null);
		}

		Process process = null;
		BufferedReader successResult = null;
		BufferedReader errorResult = null;
		StringBuilder successMsg = null;
		StringBuilder errorMsg = null;

		DataOutputStream os = null;
		try {
			process = Runtime.getRuntime().exec(
					isRoot ? COMMAND_SU : COMMAND_SH);
			os = new DataOutputStream(process.getOutputStream());
			for (String command : commands) {
				if (command == null) {
					continue;
				}

				// donnot use os.writeBytes(commmand), avoid chinese charset
				// error
				os.write(command.getBytes());
				os.writeBytes(COMMAND_LINE_END);
				os.flush();
			}
			os.writeBytes(COMMAND_EXIT);
			os.flush();

			result = process.waitFor();
			// get command result
			if (isNeedResultMsg) {
				successMsg = new StringBuilder();
				errorMsg = new StringBuilder();
				successResult = new BufferedReader(new InputStreamReader(
						process.getInputStream()));
				errorResult = new BufferedReader(new InputStreamReader(
						process.getErrorStream()));
				String s;
				while ((s = successResult.readLine()) != null) {
					successMsg.append(s);
				}
				while ((s = errorResult.readLine()) != null) {
					errorMsg.append(s);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				if (successResult != null) {
					successResult.close();
				}
				if (errorResult != null) {
					errorResult.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (process != null) {
				process.destroy();
			}
		}
		return new CommandResult(result, successMsg == null ? null
				: successMsg.toString(), errorMsg == null ? null
				: errorMsg.toString());
	}

	/**
	 * 命令的结果
	 * 
	 * @author wei2bei132
	 * 
	 */
	public static class CommandResult {

		/**
		 * 命令的结果,0代表正常,其他代表错误
		 */
		public int result;
		/**
		 * 命令结果成功的消息
		 */
		public String successMsg;
		/**
		 * 命令结果错误的消息
		 */
		public String errorMsg;

		public CommandResult(int result) {
			this.result = result;
		}

		public CommandResult(int result, String successMsg, String errorMsg) {
			this.result = result;
			this.successMsg = successMsg;
			this.errorMsg = errorMsg;
		}
	}
}
