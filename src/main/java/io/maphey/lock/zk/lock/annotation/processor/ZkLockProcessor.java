package io.maphey.lock.zk.lock.annotation.processor;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

import com.google.auto.service.AutoService;

@AutoService(Processor.class)
public class ZkLockProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> arg0, RoundEnvironment arg1) {
		return false;
	}

}
