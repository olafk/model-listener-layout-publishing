package com.liferay.kris.model.listener.publishing;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.UserNotificationEventLocalService;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


/**
 * @author Akos Thurzo
 */
@Component(immediate = true, service = ModelListener.class)
public class ModelListenerLayoutPublishing extends BaseModelListener<Layout> {
	
	@Override
	public void onAfterCreate(Layout layout) throws ModelListenerException {
		if (!layout.getSystem())
		{
			ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
			
			//ContentEditor
			//long userId = 35403;
			
			//Content Manager
			long userId = 37627;
			
			//you want to make this dynamic in reality
			String path = "/web/guest";
			String linkUrl = path + layout.getFriendlyURL();
			
			
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			jsonObject.put("notificationBody", "A page was created. The page was <i>" + layout.getNameCurrentValue() + "</i>.");
			jsonObject.put("notificationText", "A page and experience was created." );
			jsonObject.put("senderName", "Content Editors");
			jsonObject.put("linkUrl", linkUrl);
			
			
			// Send notification
			try {
				userNotificationEventLocalService.addUserNotificationEvent(userId,
						"com_liferay_kris_model_listener_publishing", (new Date()).getTime(),
						UserNotificationDeliveryConstants.TYPE_WEBSITE, userId, jsonObject.toJSONString(), false,
						serviceContext);
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}		
	}

	@Override
	public void onAfterRemove(Layout layout) throws ModelListenerException {
		if (!layout.getSystem())
		{
			ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
			
			//ContentEditor
			//long userId = 35403;
			
			//Content Manager
			long userId = 37627;
			
			//you want to make this dynamic in reality
			String path = "/group/guest/~/control_panel/manage?p_p_id=com_liferay_layout_admin_web_portlet_GroupPagesPortlet&p_p_lifecycle=0&p_p_state=maximized&p_v_l_s_g_id=20124";
			String linkUrl = path;
			
			
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			jsonObject.put("notificationBody", "A page was deleted. The page was <i>" + layout.getNameCurrentValue() + "</i>.");
			jsonObject.put("notificationText", "A page and experience was deleted." );
			jsonObject.put("senderName", "Content Editors");
			jsonObject.put("linkUrl", linkUrl);
			
			
			// Send notification
			try {
				userNotificationEventLocalService.addUserNotificationEvent(userId,
						"com_liferay_kris_model_listener_publishing", (new Date()).getTime(),
						UserNotificationDeliveryConstants.TYPE_WEBSITE, userId, jsonObject.toJSONString(), false,
						serviceContext);
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}		
		
	}

	@Override
	public void onAfterUpdate(Layout layout) throws ModelListenerException {
		if (!layout.getSystem())
		{
			ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
			
			//ContentEditor
			//long userId = 35403;
			
			//Content Manager
			long userId = 37627;
			
			//you want to make this dynamic in reality
			String path = "/web/guest";
			String linkUrl = path + layout.getFriendlyURL();
			
			
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			jsonObject.put("notificationBody", "A page was changed. The page was <i>" + layout.getNameCurrentValue() + "</i>.");
			jsonObject.put("notificationText", "A page and experience was updated." );
			jsonObject.put("senderName", "Content Editors");
			jsonObject.put("linkUrl", linkUrl);
			
			
			// Send notification
			try {
				userNotificationEventLocalService.addUserNotificationEvent(userId,
						"com_liferay_kris_model_listener_publishing", (new Date()).getTime(),
						UserNotificationDeliveryConstants.TYPE_WEBSITE, userId, jsonObject.toJSONString(), false,
						serviceContext);
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}		
	}	
	

	
	private UserNotificationEventLocalService userNotificationEventLocalService;
	
	@Reference(unbind = "-")
	public void setUserNotificationEventLocalService(
			UserNotificationEventLocalService userNotificationEventLocalService) {
		this.userNotificationEventLocalService = userNotificationEventLocalService;
	}
	
}

