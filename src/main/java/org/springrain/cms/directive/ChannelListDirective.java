package org.springrain.cms.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springrain.cms.entity.CmsChannel;
import org.springrain.cms.service.ICmsChannelService;
import org.springrain.cms.service.ICmsLinkService;
import org.springrain.cms.utils.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 栏目列表标签
 */
@Component("channelListDirective")
public class ChannelListDirective extends AbstractCMSDirective {
	
	@Resource
	private ICmsChannelService cmsChannelService;
	@Resource
	private ICmsLinkService cmsLinkService;
	

	
	
	
	/**
	 * 模板名称
	 */
	private static final String TPL_NAME = "cms_channel_list";

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		String siteId=getSiteId(params);
		
		String cacheKey=TPL_NAME+"_cache_key_"+siteId;
		
				
		
		List<CmsChannel> list = (List<CmsChannel>) getDirectiveData(cacheKey);
		if(list == null){
			try {
				list = cmsChannelService.findTreeByPid(null, siteId);
				for (CmsChannel cmsChannel : list) {//栏目内容较少，可以用遍历方式设置链接属性
					cmsChannel.setLink(cmsLinkService.findLinkBySiteBusinessId(cmsChannel.getSiteId(), cmsChannel.getId()).getLink());
				}
			} catch (Exception e) {
				list = new ArrayList<CmsChannel>();
			}
			setDirectiveData(cacheKey,list);
		}
		
		env.setVariable("channel_list", DirectiveUtils.wrap(list));
		if (body != null) { 
			body.render(env.getOut());  
		}
	}
	
	@PostConstruct
	public void  registerFreeMarkerVariable(){
		setFreeMarkerSharedVariable(TPL_NAME, this);
		
	}
	
	
}
