package com.homeloan.project.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homeloan.project.model.LoanAccount;

@Repository // Singleton Object Of Repo
public interface LoanAccountRepository extends JpaRepository<LoanAccount, String> {
	public List<LoanAccount> getBySeqid(String seqid);
}
