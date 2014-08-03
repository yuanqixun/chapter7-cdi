package me.kafeitu.activiti.chapter7.cdi.bean;

import org.activiti.cdi.BusinessProcess;
import org.activiti.cdi.annotation.BusinessProcessScoped;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by yqx on 14-8-1.
 */
@ConversationScoped
@Named("leaveAction")
public class LeaveAction implements Serializable {

	@Inject
	BusinessProcess businessProcess;

	@Inject
	Conversation conversation;

	@Inject
	SystemUser systemUser;

	private String pdfKey;

	private Leave leave;

	@PostConstruct
	void afterCreate() {
		leave = new Leave();
		leave.setStartTime(String.format("%tF", new Date()));
		pdfKey = getRequestParameter("processDefinitionKey");
	}

	public Leave getLeave() {
		return leave;
	}

	public void setLeave(Leave leave) {
		this.leave = leave;
	}

	public String onLeaveApply() {
		String pdfKey = getPdfKey();
		if (StringUtils.isNotBlank(pdfKey)) {
			businessProcess.startProcessByKey(pdfKey);
			systemUser.setUserId("deptLeader");
			return "taskList.xhtml";
		} else
			return "";
	}

	public String getPdfKey() {
		return pdfKey;
	}

	public void setPdfKey(String pdfKey) {
		this.pdfKey = pdfKey;
	}

	public void beginConversation(ComponentSystemEvent e) {
		if (conversation.isTransient()) {
			conversation.begin();
			conversation.setTimeout(36000000);
		}
	}

	/**
	 * 获得指定key的请求参数值
	 *
	 * @param key
	 * @return
	 */
	protected String getRequestParameter(String key) {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> map = context.getExternalContext().getRequestParameterMap();
		String value = map.get(key);
		if (StringUtils.isBlank(value))
			return "";
		return value;
	}
}
