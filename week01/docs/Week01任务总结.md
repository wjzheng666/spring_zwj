# 一、仓库关联与推送问题

1.	#### 问题：Repository not found（仓库未找到）

原因：远程仓库地址错误、仓库未创建、用户名或仓库名拼写错误。
	解决方法：
	o	确认 GitHub 上已创建对应仓库；
	o	检查用户名与仓库名是否完全一致；
	o	使用 git remote set-url 命令修改远程地址。
2.	#### 问题：fatal: couldn't find remote ref main

原因：本地分支是 master，推送时却使用 main，分支不匹配。
	解决方法：
	o	将本地分支重命名为 main：git branch -m master main；
	o	或直接推送到 master 分支。
3.	#### 问题：failed to push some refs 推送失败

原因：远程仓库有本地没有的提交，版本不一致。
	解决方法：
	o	先拉取合并：git pull origin main --allow-unrelated-histories；
	o	再执行推送：git push -u origin main。
4.	#### 问题：将 HTTPS 地址改为 SSH 地址

原因：已存在 origin，不能重复添加。
	解决方法：
	o	使用命令：git remote set-url origin git@github.com:wjzheng666/springboot_zwj.git。
________________________________________
# 二、Git 操作流程问题

1.	#### 问题：进入合并提交编辑页面（vim 界面）

原因：git pull 时需要生成合并提交。
	解决方法：
	o	按 Esc；
	o	输入 :wq 回车保存退出。
2. #### 问题：nothing to commit, working tree clean

   原因：本地没有修改，所有内容已提交。
   解决方法：直接 git push 推送即可。
3.	#### 问题：修改内容后重新提交

	标准流程：
	o	git add .
	o	git commit -m "说明内容"
	o	git push
________________________________________
# 三、GitHub 仓库管理问题

1.	#### 问题：仓库页面显示为空

原因：当前分支无内容，或默认分支不是 main。
	解决方法：
	o	切换到 main 分支查看；
	o	在 Settings → Branches 中将 main 设为默认分支。
2.	#### 问题：删除不需要的仓库

	解决方法：
	o	进入仓库 Settings；
	o	拉到最底部 Danger Zone；
	o	点击 Delete this repository；
	o	输入完整仓库名确认删除。