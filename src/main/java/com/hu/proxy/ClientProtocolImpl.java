package com.hu.proxy;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.crypto.CryptoProtocolVersion;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.AclEntry;
import org.apache.hadoop.fs.permission.AclStatus;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.ha.HAServiceProtocol;
import org.apache.hadoop.hdfs.AddBlockFlag;
import org.apache.hadoop.hdfs.NameNodeProxies;
import org.apache.hadoop.hdfs.NameNodeProxiesClient;
import org.apache.hadoop.hdfs.inotify.EventBatchList;
import org.apache.hadoop.hdfs.protocol.*;
import org.apache.hadoop.hdfs.security.token.block.DataEncryptionKey;
import org.apache.hadoop.hdfs.security.token.delegation.DelegationTokenIdentifier;
import org.apache.hadoop.hdfs.server.protocol.DatanodeStorageReport;
import org.apache.hadoop.io.EnumSetWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.ipc.Server;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.security.token.Token;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ClientProtocolImpl implements ClientProtocol {

    public static final Map<String, ClientProtocol> map = new HashMap<>();
    public static URI uri;

    static {
        try {
            uri = new URI("hdfs://host:port");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private ClientProtocol getClientProtocol() throws IOException {
        UserGroupInformation ugi = Server.getRemoteUser();
        ClientProtocol clientProtocol = map.get(ugi.getUserName());
        if (clientProtocol == null) {
            UserGroupInformation.setLoginUser(ugi);
            NameNodeProxiesClient.ProxyAndInfo<ClientProtocol> proxy = NameNodeProxies.createProxy(new Configuration(),
                    uri, ClientProtocol.class);
            clientProtocol = proxy.getProxy();
            map.put(ugi.getUserName(), clientProtocol);
        }
        return clientProtocol;
    }

    @Override
    public LocatedBlocks getBlockLocations(String src, long offset, long length) throws IOException {
        return null;
    }

    @Override
    public FsServerDefaults getServerDefaults() throws IOException {
        return null;
    }

    @Override
    public HdfsFileStatus create(String src, FsPermission masked, String clientName, EnumSetWritable<CreateFlag> flag, boolean createParent, short replication, long blockSize, CryptoProtocolVersion[] supportedVersions, String ecPolicyName) throws IOException {
        return null;
    }

    @Override
    public LastBlockWithStatus append(String src, String clientName, EnumSetWritable<CreateFlag> flag) throws IOException {
        return null;
    }

    @Override
    public boolean setReplication(String src, short replication) throws IOException {
        return false;
    }

    @Override
    public BlockStoragePolicy[] getStoragePolicies() throws IOException {
        return new BlockStoragePolicy[0];
    }

    @Override
    public void setStoragePolicy(String src, String policyName) throws IOException {

    }

    @Override
    public void unsetStoragePolicy(String src) throws IOException {

    }

    @Override
    public BlockStoragePolicy getStoragePolicy(String path) throws IOException {
        return null;
    }

    @Override
    public void setPermission(String src, FsPermission permission) throws IOException {

    }

    @Override
    public void setOwner(String src, String username, String groupname) throws IOException {

    }

    @Override
    public void abandonBlock(ExtendedBlock b, long fileId, String src, String holder) throws IOException {

    }

    @Override
    public LocatedBlock addBlock(String src, String clientName, ExtendedBlock previous, DatanodeInfo[] excludeNodes, long fileId, String[] favoredNodes, EnumSet<AddBlockFlag> addBlockFlags) throws IOException {
        return null;
    }

    @Override
    public LocatedBlock getAdditionalDatanode(String src, long fileId, ExtendedBlock blk, DatanodeInfo[] existings, String[] existingStorageIDs, DatanodeInfo[] excludes, int numAdditionalNodes, String clientName) throws IOException {
        return null;
    }

    @Override
    public boolean complete(String src, String clientName, ExtendedBlock last, long fileId) throws IOException {
        return false;
    }

    @Override
    public void reportBadBlocks(LocatedBlock[] blocks) throws IOException {

    }

    @Override
    public boolean rename(String src, String dst) throws IOException {
        return false;
    }

    @Override
    public void concat(String trg, String[] srcs) throws IOException {

    }

    @Override
    public void rename2(String src, String dst, Options.Rename... options) throws IOException {

    }

    @Override
    public boolean truncate(String src, long newLength, String clientName) throws IOException {
        return false;
    }

    @Override
    public boolean delete(String src, boolean recursive) throws IOException {
        return false;
    }

    @Override
    public boolean mkdirs(String src, FsPermission masked, boolean createParent) throws IOException {
        return false;
    }

    @Override
    public DirectoryListing getListing(String src, byte[] startAfter, boolean needLocation) throws IOException {
        return getClientProtocol().getListing(src, startAfter, needLocation);
    }

    @Override
    public SnapshottableDirectoryStatus[] getSnapshottableDirListing() throws IOException {
        return new SnapshottableDirectoryStatus[0];
    }

    @Override
    public void renewLease(String clientName) throws IOException {

    }

    @Override
    public boolean recoverLease(String src, String clientName) throws IOException {
        return false;
    }

    @Override
    public long[] getStats() throws IOException {
        return new long[0];
    }

    @Override
    public ReplicatedBlockStats getReplicatedBlockStats() throws IOException {
        return null;
    }

    @Override
    public ECBlockGroupStats getECBlockGroupStats() throws IOException {
        return null;
    }

    @Override
    public DatanodeInfo[] getDatanodeReport(HdfsConstants.DatanodeReportType type) throws IOException {
        return new DatanodeInfo[0];
    }

    @Override
    public DatanodeStorageReport[] getDatanodeStorageReport(HdfsConstants.DatanodeReportType type) throws IOException {
        return new DatanodeStorageReport[0];
    }

    @Override
    public long getPreferredBlockSize(String filename) throws IOException {
        return 0;
    }

    @Override
    public boolean setSafeMode(HdfsConstants.SafeModeAction action, boolean isChecked) throws IOException {
        return false;
    }

    @Override
    public boolean saveNamespace(long timeWindow, long txGap) throws IOException {
        return false;
    }

    @Override
    public long rollEdits() throws IOException {
        return 0;
    }

    @Override
    public boolean restoreFailedStorage(String arg) throws IOException {
        return false;
    }

    @Override
    public void refreshNodes() throws IOException {

    }

    @Override
    public void finalizeUpgrade() throws IOException {

    }

    @Override
    public boolean upgradeStatus() throws IOException {
        return false;
    }

    @Override
    public RollingUpgradeInfo rollingUpgrade(HdfsConstants.RollingUpgradeAction action) throws IOException {
        return null;
    }

    @Override
    public CorruptFileBlocks listCorruptFileBlocks(String path, String cookie) throws IOException {
        return null;
    }

    @Override
    public void metaSave(String filename) throws IOException {

    }

    @Override
    public void setBalancerBandwidth(long bandwidth) throws IOException {

    }

    @Override
    public HdfsFileStatus getFileInfo(String src) throws IOException {
        return getClientProtocol().getFileInfo(src);
    }

    @Override
    public boolean isFileClosed(String src) throws IOException {
        return false;
    }

    @Override
    public HdfsFileStatus getFileLinkInfo(String src) throws IOException {
        return null;
    }

    @Override
    public HdfsLocatedFileStatus getLocatedFileInfo(String src, boolean needBlockToken) throws IOException {
        return null;
    }

    @Override
    public ContentSummary getContentSummary(String path) throws IOException {
        return null;
    }

    @Override
    public void setQuota(String path, long namespaceQuota, long storagespaceQuota, StorageType type) throws IOException {

    }

    @Override
    public void fsync(String src, long inodeId, String client, long lastBlockLength) throws IOException {

    }

    @Override
    public void setTimes(String src, long mtime, long atime) throws IOException {

    }

    @Override
    public void createSymlink(String target, String link, FsPermission dirPerm, boolean createParent) throws IOException {

    }

    @Override
    public String getLinkTarget(String path) throws IOException {
        return null;
    }

    @Override
    public LocatedBlock updateBlockForPipeline(ExtendedBlock block, String clientName) throws IOException {
        return null;
    }

    @Override
    public void updatePipeline(String clientName, ExtendedBlock oldBlock, ExtendedBlock newBlock, DatanodeID[] newNodes, String[] newStorageIDs) throws IOException {

    }

    @Override
    public Token<DelegationTokenIdentifier> getDelegationToken(Text renewer) throws IOException {
        return null;
    }

    @Override
    public long renewDelegationToken(Token<DelegationTokenIdentifier> token) throws IOException {
        return 0;
    }

    @Override
    public void cancelDelegationToken(Token<DelegationTokenIdentifier> token) throws IOException {

    }

    @Override
    public DataEncryptionKey getDataEncryptionKey() throws IOException {
        return null;
    }

    @Override
    public String createSnapshot(String snapshotRoot, String snapshotName) throws IOException {
        return null;
    }

    @Override
    public void deleteSnapshot(String snapshotRoot, String snapshotName) throws IOException {

    }

    @Override
    public void renameSnapshot(String snapshotRoot, String snapshotOldName, String snapshotNewName) throws IOException {

    }

    @Override
    public void allowSnapshot(String snapshotRoot) throws IOException {

    }

    @Override
    public void disallowSnapshot(String snapshotRoot) throws IOException {

    }

    @Override
    public SnapshotDiffReport getSnapshotDiffReport(String snapshotRoot, String fromSnapshot, String toSnapshot) throws IOException {
        return null;
    }

    @Override
    public SnapshotDiffReportListing getSnapshotDiffReportListing(String snapshotRoot, String fromSnapshot, String toSnapshot, byte[] startPath, int index) throws IOException {
        return null;
    }

    @Override
    public long addCacheDirective(CacheDirectiveInfo directive, EnumSet<CacheFlag> flags) throws IOException {
        return 0;
    }

    @Override
    public void modifyCacheDirective(CacheDirectiveInfo directive, EnumSet<CacheFlag> flags) throws IOException {

    }

    @Override
    public void removeCacheDirective(long id) throws IOException {

    }

    @Override
    public BatchedRemoteIterator.BatchedEntries<CacheDirectiveEntry> listCacheDirectives(long prevId, CacheDirectiveInfo filter) throws IOException {
        return null;
    }

    @Override
    public void addCachePool(CachePoolInfo info) throws IOException {

    }

    @Override
    public void modifyCachePool(CachePoolInfo req) throws IOException {

    }

    @Override
    public void removeCachePool(String pool) throws IOException {

    }

    @Override
    public BatchedRemoteIterator.BatchedEntries<CachePoolEntry> listCachePools(String prevPool) throws IOException {
        return null;
    }

    @Override
    public void modifyAclEntries(String src, List<AclEntry> aclSpec) throws IOException {

    }

    @Override
    public void removeAclEntries(String src, List<AclEntry> aclSpec) throws IOException {

    }

    @Override
    public void removeDefaultAcl(String src) throws IOException {

    }

    @Override
    public void removeAcl(String src) throws IOException {

    }

    @Override
    public void setAcl(String src, List<AclEntry> aclSpec) throws IOException {

    }

    @Override
    public AclStatus getAclStatus(String src) throws IOException {
        return null;
    }

    @Override
    public void createEncryptionZone(String src, String keyName) throws IOException {

    }

    @Override
    public EncryptionZone getEZForPath(String src) throws IOException {
        return null;
    }

    @Override
    public BatchedRemoteIterator.BatchedEntries<EncryptionZone> listEncryptionZones(long prevId) throws IOException {
        return null;
    }

    @Override
    public void reencryptEncryptionZone(String zone, HdfsConstants.ReencryptAction action) throws IOException {

    }

    @Override
    public BatchedRemoteIterator.BatchedEntries<ZoneReencryptionStatus> listReencryptionStatus(long prevId) throws IOException {
        return null;
    }

    @Override
    public void setXAttr(String src, XAttr xAttr, EnumSet<XAttrSetFlag> flag) throws IOException {

    }

    @Override
    public List<XAttr> getXAttrs(String src, List<XAttr> xAttrs) throws IOException {
        return null;
    }

    @Override
    public List<XAttr> listXAttrs(String src) throws IOException {
        return null;
    }

    @Override
    public void removeXAttr(String src, XAttr xAttr) throws IOException {

    }

    @Override
    public void checkAccess(String path, FsAction mode) throws IOException {

    }

    @Override
    public long getCurrentEditLogTxid() throws IOException {
        return 0;
    }

    @Override
    public EventBatchList getEditsFromTxid(long txid) throws IOException {
        return null;
    }

    @Override
    public void setErasureCodingPolicy(String src, String ecPolicyName) throws IOException {

    }

    @Override
    public AddErasureCodingPolicyResponse[] addErasureCodingPolicies(ErasureCodingPolicy[] policies) throws IOException {
        return new AddErasureCodingPolicyResponse[0];
    }

    @Override
    public void removeErasureCodingPolicy(String ecPolicyName) throws IOException {

    }

    @Override
    public void enableErasureCodingPolicy(String ecPolicyName) throws IOException {

    }

    @Override
    public void disableErasureCodingPolicy(String ecPolicyName) throws IOException {

    }

    @Override
    public ErasureCodingPolicyInfo[] getErasureCodingPolicies() throws IOException {
        return new ErasureCodingPolicyInfo[0];
    }

    @Override
    public Map<String, String> getErasureCodingCodecs() throws IOException {
        return null;
    }

    @Override
    public ErasureCodingPolicy getErasureCodingPolicy(String src) throws IOException {
        return null;
    }

    @Override
    public void unsetErasureCodingPolicy(String src) throws IOException {

    }

    @Override
    public QuotaUsage getQuotaUsage(String path) throws IOException {
        return null;
    }

    @Override
    public BatchedRemoteIterator.BatchedEntries<OpenFileEntry> listOpenFiles(long prevId) throws IOException {
        return null;
    }

    @Override
    public BatchedRemoteIterator.BatchedEntries<OpenFileEntry> listOpenFiles(long prevId, EnumSet<OpenFilesIterator.OpenFilesType> openFilesTypes, String path) throws IOException {
        return null;
    }

    @Override
    public HAServiceProtocol.HAServiceState getHAServiceState() throws IOException {
        return null;
    }

    @Override
    public void msync() throws IOException {

    }

    @Override
    public void satisfyStoragePolicy(String path) throws IOException {

    }
}
