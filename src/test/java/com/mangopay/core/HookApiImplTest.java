package com.mangopay.core;

import com.mangopay.core.enumerations.EventType;
import com.mangopay.entities.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * HookApiImpl test methods.
 */
public class HookApiImplTest extends BaseTest {

    @Test
    public void createHook() {
        try {
            Hook hook = this.getJohnsHook();
            assertTrue(hook.getId().length() > 0);
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void getHook() {
        try {
            Hook hook = this.getJohnsHook();
            Hook getHook = this.api.getHookApi().get(hook.getId());

            assertEquals(getHook.getEventType(), hook.getEventType());
            assertEquals(getHook.getId(), hook.getId());
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void updateHook() {
        try {
            Hook hook = this.getJohnsHook();
            hook.setUrl("http://test123.com");

            Hook saveHook = this.api.getHookApi().update(hook);

            assertEquals(saveHook.getId(), hook.getId());
            assertEquals(saveHook.getUrl(), "http://test123.com");
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void getHooks() {
        try {
            Hook hook = this.getJohnsHook();
            Pagination pagination = new Pagination(1, 1);

            List<Hook> list = this.api.getHookApi().getAll(pagination, null);

            assertTrue(list.get(0) instanceof Hook);
            assertEquals(hook.getId(), list.get(0).getId());
            assertEquals(pagination.getPage(), 1);
            assertEquals(pagination.getItemsPerPage(), 1);
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void createUserInflowHooks() {
        try {
            Hook hook = new Hook();
            hook.setEventType(EventType.USER_INFLOWS_BLOCKED);
            hook.setUrl("http://test.com");
            Hook inflowBlockedHook = this.api.getHookApi().create(hook);

            hook.setEventType(EventType.USER_INFLOWS_UNBLOCKED);
            hook.setUrl("http://test.com");
            Hook inflowUnblockedHook = this.api.getHookApi().create(hook);

            assertNotNull(inflowBlockedHook);
            assertNotNull(inflowUnblockedHook);
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void createUserOutflowHooks() {
        try {
            Hook hook = new Hook();

            hook.setEventType(EventType.USER_OUTFLOWS_BLOCKED);
            hook.setUrl("http://test.com");
            Hook outflowBlockedHook = this.api.getHookApi().create(hook);

            hook.setEventType(EventType.USER_OUTFLOWS_UNBLOCKED);
            hook.setUrl("http://test.com");
            Hook outflowUnblockedHook = this.api.getHookApi().create(hook);

            assertNotNull(outflowBlockedHook);
            assertNotNull(outflowUnblockedHook);
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }
}
