/*
 * Copyright 2014 Open Networking Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onosproject.dhcp.impl;

import org.onlab.packet.Ip4Address;
import org.onlab.packet.MacAddress;
import org.onosproject.core.ApplicationId;
import org.onosproject.net.config.Config;
import org.onosproject.net.config.basics.BasicElementConfig;

/**
 * DHCP Config class.
 */
public class DhcpConfig extends Config<ApplicationId> {

    public static final String MY_IP = "ip";
    public static final String MY_MAC = "mac";
    public static final String SUBNET_MASK = "subnet";
    public static final String BROADCAST_ADDRESS = "broadcast";
    public static final String ROUTER_ADDRESS = "router";
    public static final String DOMAIN_SERVER = "domain";
    public static final String TTL = "ttl";
    public static final String LEASE_TIME = "lease";
    public static final String RENEW_TIME = "renew";
    public static final String REBIND_TIME = "rebind";
    public static final String TIMER_DELAY = "delay";
    public static final String DEFAULT_TIMEOUT = "timeout";
    public static final String START_IP = "startip";
    public static final String END_IP = "endip";

    /**
     * Returns the dhcp server ip.
     *
     * @return ip address or null if not set
     */
    public Ip4Address ip() {
        String ip = get(MY_IP, null);
        return ip != null ? Ip4Address.valueOf(ip) : null;
    }

    /**
     * Sets the dhcp server ip.
     *
     * @param ip new ip address; null to clear
     * @return self
     */
    public BasicElementConfig ip(String ip) {
        return (BasicElementConfig) setOrClear(MY_IP, ip);
    }

    /**
     * Returns the dhcp server mac.
     *
     * @return server mac or null if not set
     */
    public MacAddress mac() {
        String mac = get(MY_MAC, null);
        return mac != null ? MacAddress.valueOf(mac) : null;
    }

    /**
     * Sets the dhcp server mac.
     *
     * @param mac new mac address; null to clear
     * @return self
     */
    public BasicElementConfig mac(String mac) {
        return (BasicElementConfig) setOrClear(MY_MAC, mac);
    }

    /**
     * Returns the subnet mask.
     *
     * @return subnet mask or null if not set
     */
    public Ip4Address subnetMask() {
        String ip = get(SUBNET_MASK, null);
        return ip != null ? Ip4Address.valueOf(ip) : null;
    }

    /**
     * Sets the subnet mask.
     *
     * @param subnet new subnet mask; null to clear
     * @return self
     */
    public BasicElementConfig subnetMask(String subnet) {
        return (BasicElementConfig) setOrClear(SUBNET_MASK, subnet);
    }

    /**
     * Returns the broadcast address.
     *
     * @return broadcast address or null if not set
     */
    public Ip4Address broadcastAddress() {
        String ip = get(BROADCAST_ADDRESS, null);
        return ip != null ? Ip4Address.valueOf(ip) : null;
    }

    /**
     * Sets the broadcast address.
     *
     * @param broadcast new broadcast address; null to clear
     * @return self
     */
    public BasicElementConfig broadcastAddress(String broadcast) {
        return (BasicElementConfig) setOrClear(BROADCAST_ADDRESS, broadcast);
    }

    /**
     * Returns the Time To Live for the reply packets.
     *
     * @return ttl or null if not set
     */
    public int ttl() {
        return get(TTL, 0);
    }

    /**
     * Sets the Time To Live for the reply packets.
     *
     * @param ttl new ttl; null to clear
     * @return self
     */
    public BasicElementConfig ttl(int ttl) {
        return (BasicElementConfig) setOrClear(TTL, ttl);
    }

    /**
     * Returns the Lease Time offered by the DHCP Server.
     *
     * @return lease time or null if not set
     */
    public int leaseTime() {
        return get(LEASE_TIME, 0);
    }

    /**
     * Sets the Lease Time offered by the DHCP Server.
     *
     * @param lease new lease time; null to clear
     * @return self
     */
    public BasicElementConfig leaseTime(int lease) {
        return (BasicElementConfig) setOrClear(LEASE_TIME, lease);
    }

    /**
     * Returns the Renew Time offered by the DHCP Server.
     *
     * @return renew time or null if not set
     */
    public int renewTime() {
        return get(RENEW_TIME, 0);
    }

    /**
     * Sets the Renew Time offered by the DHCP Server.
     *
     * @param renew new renew time; null to clear
     * @return self
     */
    public BasicElementConfig renewTime(int renew) {
        return (BasicElementConfig) setOrClear(RENEW_TIME, renew);
    }

    /**
     * Returns the Rebind Time offered by the DHCP Server.
     *
     * @return rebind time or null if not set
     */
    public int rebindTime() {
        return get(REBIND_TIME, 0);
    }

    /**
     * Sets the Rebind Time offered by the DHCP Server.
     *
     * @param rebind new rebind time; null to clear
     * @return self
     */
    public BasicElementConfig rebindTime(int rebind) {
        return (BasicElementConfig) setOrClear(REBIND_TIME, rebind);
    }

    /**
     * Returns the Router Address.
     *
     * @return router address or null if not set
     */
    public Ip4Address routerAddress() {
        String ip = get(ROUTER_ADDRESS, null);
        return ip != null ? Ip4Address.valueOf(ip) : null;
    }

    /**
     * Sets the Router Address.
     *
     * @param router new router address; null to clear
     * @return self
     */
    public BasicElementConfig routerAddress(String router) {
        return (BasicElementConfig) setOrClear(ROUTER_ADDRESS, router);
    }

    /**
     * Returns the Domain Server Address.
     *
     * @return domain server address or null if not set
     */
    public Ip4Address domainServer() {
        String ip = get(DOMAIN_SERVER, null);
        return ip != null ? Ip4Address.valueOf(ip) : null;
    }

    /**
     * Sets the Domain Server Address.
     *
     * @param domain new domain server address; null to clear
     * @return self
     */
    public BasicElementConfig domainServer(String domain) {
        return (BasicElementConfig) setOrClear(DOMAIN_SERVER, domain);
    }

    /**
     * Returns the delay after which the dhcp server will purge expired entries.
     *
     * @return time delay or null if not set
     */
    public int timerDelay() {
        return get(TIMER_DELAY, 0);
    }

    /**
     * Sets the delay after which the dhcp server will purge expired entries.
     *
     * @param delay new time delay; null to clear
     * @return self
     */
    public BasicElementConfig timerDelay(int delay) {
        return (BasicElementConfig) setOrClear(TIMER_DELAY, delay);
    }

    /**
     * Returns the default timeout for pending assignments.
     *
     * @return default timeout or null if not set
     */
    public int defaultTimeout() {
        return get(DEFAULT_TIMEOUT, 0);
    }

    /**
     * Sets the default timeout for pending assignments.
     *
     * @param defaultTimeout new default timeout; null to clear
     * @return self
     */
    public BasicElementConfig defaultTimeout(int defaultTimeout) {
        return (BasicElementConfig) setOrClear(DEFAULT_TIMEOUT, defaultTimeout);
    }

    /**
     * Returns the start IP for the available IP Range.
     *
     * @return start IP or null if not set
     */
    public Ip4Address startIp() {
        String ip = get(START_IP, null);
        return ip != null ? Ip4Address.valueOf(ip) : null;
    }

    /**
     * Sets the start IP for the available IP Range.
     *
     * @param startIp new start IP; null to clear
     * @return self
     */
    public BasicElementConfig startIp(String startIp) {
        return (BasicElementConfig) setOrClear(START_IP, startIp);
    }

    /**
     * Returns the end IP for the available IP Range.
     *
     * @return end IP or null if not set
     */
    public Ip4Address endIp() {
        String ip = get(END_IP, null);
        return ip != null ? Ip4Address.valueOf(ip) : null;
    }

    /**
     * Sets the end IP for the available IP Range.
     *
     * @param endIp new end IP; null to clear
     * @return self
     */
    public BasicElementConfig endIp(String endIp) {
        return (BasicElementConfig) setOrClear(END_IP, endIp);
    }
}
